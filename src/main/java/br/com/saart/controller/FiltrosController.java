package br.com.saart.controller;

import atlantafx.base.theme.Styles;
import br.com.saart.specification.CustomSpecification;
import br.com.saart.task.filtros.*;
import br.com.saart.view.StageFactory;
import br.com.saart.view.controls.Components;
import br.com.saart.view.filtro.FiltroCallback;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;

import java.net.URL;
import java.util.*;

import static br.com.saart.task.filtros.Juncao.*;
import static br.com.saart.task.filtros.TipoFlow.*;
import static javafx.scene.control.Alert.AlertType.ERROR;

@Slf4j
@Controller
public class FiltrosController implements Initializable {

    @Autowired
    private StageFactory stageFactory;

    @Value("/view/filtros.fxml")
    private ClassPathResource scene;

    Stage stage;

    public ChoiceBox<Coluna> colunaFiltro;
    public ChoiceBox<Operador> operadorFiltro;
    public TextField valorFiltro;
    public TextFlow filtro;

    public ChoiceBox<Coluna> colunaOrdem;
    public ChoiceBox<Ordem> operadorOrdem;
    public TextFlow ordem;

    private String erroValidacao = null;
    private CustomSpecification<?> customSpecification = null;
    private FiltroCallback callback = null;
    private String lastCallerClass = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        operadorFiltro.setItems(FXCollections.observableArrayList(Operador.values()));
        operadorFiltro.getSelectionModel().select(0);
        operadorOrdem.setItems(FXCollections.observableArrayList(Ordem.values()));
        operadorOrdem.getSelectionModel().select(0);

        valorFiltro.disableProperty().bind(operadorFiltro.valueProperty().isEqualTo(Operador.NULL)
                .or(operadorFiltro.valueProperty().isEqualTo(Operador.NOT_NULL)));
    }

    public void montar() {
        if (stage == null) {
            stage = stageFactory.createStage(scene, "Filtros e Ordenação");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.toFront();
        }
    }

    public void abrir(List<Coluna> colunas, CustomSpecification<?> customSpecification, FiltroCallback callback) {
        String callerClass = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass().getName();
        if (!callerClass.equals(lastCallerClass)) {
            this.lastCallerClass = callerClass;
            this.filtro.getChildren().clear();
            this.ordem.getChildren().clear();
        }

        this.customSpecification = customSpecification;
        this.callback = callback;
        this.erroValidacao = null;

        colunaFiltro.setItems(FXCollections.observableList(colunas));
        colunaFiltro.getSelectionModel().select(0);

        colunaOrdem.setItems(FXCollections.observableList(colunas));
        colunaOrdem.getSelectionModel().select(0);

        stage.showAndWait();
    }

    public void filtrar(ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof SplitMenuButton && (valorFiltro.disabledProperty().get() || valorFiltro.textProperty().isNotEmpty().get())) {
            addColuna(filtro, colunaFiltro);
            addOperador(filtro, operadorFiltro);
            if (valorFiltro.disabledProperty().not().get()) {
                addValor(filtro, valorFiltro.getText());
                valorFiltro.clear();
            }
        } else if (actionEvent.getSource() instanceof MenuItem menuItem) {
            if ("limparTodosOsFiltros".equals(menuItem.getId())) {
                filtro.getChildren().clear();
            } else {
                addJuncao(filtro, Juncao.valueOf(menuItem.getId()));
            }
        }
    }

    public void ordenar(ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof SplitMenuButton) {
            if (!ordem.getChildren().isEmpty()) {
                addJuncao(ordem, AND);
            }
            addColuna(ordem, colunaOrdem);
            addOrdem(ordem, operadorOrdem);
        } else if (actionEvent.getSource() instanceof MenuItem menuItem) {
            if ("limparTodasAsOrdens".equals(menuItem.getId())) {
                ordem.getChildren().clear();
            }
        }
    }

    private void addColuna(TextFlow flow, ChoiceBox<Coluna> colunaChoiceBox) {
        Coluna coluna = colunaChoiceBox.getSelectionModel().getSelectedItem();
        Label label = new Label(coluna.label());
        label.setUserData(new FiltroOrdemUserData(COL, coluna, null, null, null, null));
        flow.getChildren().add(label);
    }

    private void addOperador(TextFlow flow, ChoiceBox<Operador> operadorChoiceBox) {
        Operador operador = operadorChoiceBox.getSelectionModel().getSelectedItem();
        Label label = new Label(operador.getLabel());
        label.setUserData(new FiltroOrdemUserData(OPR, null, operador, null, null, null));
        label.getStyleClass().add(Styles.ACCENT);
        flow.getChildren().add(label);
    }

    private void addValor(TextFlow flow, String valor) {
        Label label = new Label(valor);
        label.setUserData(new FiltroOrdemUserData(VLR, null, null, valor, null, null));
        flow.getChildren().add(label);
    }

    private void addJuncao(TextFlow flow, Juncao juncao) {
        Label label = new Label(juncao.getLabel());
        label.setUserData(new FiltroOrdemUserData(JUN, null, null, null, juncao, null));
        label.getStyleClass().add(Styles.DANGER);
        flow.getChildren().add(label);
    }

    private void addOrdem(TextFlow flow, ChoiceBox<Ordem> operadorChoiceBox) {
        Ordem ordem = operadorChoiceBox.getSelectionModel().getSelectedItem();
        Label label = new Label(ordem.getLabel());
        label.setUserData(new FiltroOrdemUserData(ORD, null, null, null, null, ordem));
        label.getStyleClass().add(Styles.ACCENT);
        flow.getChildren().add(label);
    }

    public void aplicar() {
        List<FiltroOrdemUserData> filtroList = filtro.getChildren().stream().map(n -> (FiltroOrdemUserData) n.getUserData()).toList();
        List<FiltroOrdemUserData> ordemList = ordem.getChildren().stream().map(n -> (FiltroOrdemUserData) n.getUserData()).toList();

        if (!validaFiltro(filtroList)) {
            Components.alert(ERROR, stage.getTitle(), "Filtro inválido.", erroValidacao, false);
        } else {
            Specification<?> specification = customSpecification.criarSpecification(filtroList);
            Sort sort = criaSort(ordemList);

            stage.close();
            callback.execute(specification, sort);
        }
    }

    private boolean validaFiltro(List<FiltroOrdemUserData> operationList) {
        Deque<FiltroOrdemUserData> parentesesStack = new LinkedList<>();
        Deque<FiltroOrdemUserData> operacoesStack = new LinkedList<>();

        boolean ultimoEhJuncao = false;
        boolean juncaoEmParenteses = false;

        for (FiltroOrdemUserData token : operationList) {
            if (COL.equals(token.tipo()) || VLR.equals(token.tipo())) {
                // Ignora colunas e valores
                continue;
            } else if (JUN.equals(token.tipo())) {
                if (OPEN.equals(token.juncao())) {
                    // Abertura de parênteses
                    if (!operacoesStack.isEmpty()) {
                        erroValidacao = "Certifique-se de que os parênteses estejam corretamente abertos. Você pode abrir parênteses apenas no início do filtro ou após um operador 'E' ou 'Ou'.";
                        return false;
                    }
                    parentesesStack.push(token);
                    juncaoEmParenteses = false;
                } else if (CLOSE.equals(token.juncao())) {
                    // Fechamento de parênteses
                    if (parentesesStack.isEmpty()) {
                        erroValidacao = "Verifique os parênteses. Certifique-se de que o número de parênteses fechados seja igual ao número de parênteses abertos.";
                        return false; // Parenteses não correspondentes
                    } else if (!juncaoEmParenteses) {
                        erroValidacao = "Confira as operações dentro dos parênteses. É preciso ter no mínimo uma operação 'E' ou 'Ou' dentro dos parênteses.";
                        return false; // Parenteses fechando antes da hora
                    }
                    parentesesStack.pop();
                } else { //AND,OR
                    // Operadores de junção
                    if (operacoesStack.isEmpty()) {
                        erroValidacao = "Revise as operações com 'E' ou 'Ou'. Lembre-se de usá-las somente após uma comparação ou após fechar um parêntese.";
                        return false; // Falta de operandos para o operador
                    }
                    operacoesStack.clear();
                    ultimoEhJuncao = true;
                    juncaoEmParenteses = !parentesesStack.isEmpty();
                }
            } else if (!operacoesStack.isEmpty()) {
                // Comparações consecutivas
                erroValidacao = "Analise as comparações realizadas. Evite fazer comparações seguidas sem usar um operador 'E' ou 'Ou' para conectá-las.";
                return false;
            } else {
                // Adiciona operações
                operacoesStack.push(token);
                ultimoEhJuncao = false;
            }
        }

        // Validações finais
        if (!parentesesStack.isEmpty()) {
            erroValidacao = "Verifique os parênteses. Garanta que todos os parênteses abertos tenham seu correspondente fechamento.";
            return false;
        }

        if (ultimoEhJuncao) {
            erroValidacao = "Verifique os operadores 'E' ou 'Ou'. Não termine o filtro com um desses operadores; sempre finalize com uma comparação.";
            return false;
        }

        return true;
    }

    private Sort criaSort(List<FiltroOrdemUserData> ordemList) {
        if (CollectionUtils.isEmpty(ordemList)) {
            return Sort.unsorted();
        }

        FiltroOrdemUserData ultimaColuna = null;
        List<Sort.Order> ordenacao = new ArrayList<>();

        for (FiltroOrdemUserData ordem : ordemList) {
            if (TipoFlow.COL.equals(ordem.tipo())) {
                ultimaColuna = ordem;
            } else if (TipoFlow.ORD.equals(ordem.tipo())) {
                ordenacao.add(new Sort.Order(ordem.ordem().getDirecao(), ultimaColuna.coluna().nome()));
            }
        }

        return Sort.by(ordenacao);
    }
}

