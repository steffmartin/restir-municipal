package br.com.saart.controller;

import atlantafx.base.theme.Styles;
import br.com.saart.task.filtros.*;
import br.com.saart.view.StageFactory;
import br.com.saart.view.controls.Components;
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
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

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

    public void abrir(List<Coluna> colunas) {
        colunaFiltro.setItems(FXCollections.observableList(colunas));
        colunaFiltro.getSelectionModel().select(0);
        colunaOrdem.setItems(FXCollections.observableList(colunas));
        colunaOrdem.getSelectionModel().select(0);
        erroValidacao = null;
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
                addJuncao(ordem, Juncao.AND);
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
        label.setUserData(new FiltroOrdemUserData(TipoFlow.COL, coluna, null, null, null, null));
        flow.getChildren().add(label);
    }

    private void addOperador(TextFlow flow, ChoiceBox<Operador> operadorChoiceBox) {
        Operador operador = operadorChoiceBox.getSelectionModel().getSelectedItem();
        Label label = new Label(operador.getLabel());
        label.setUserData(new FiltroOrdemUserData(TipoFlow.OPR, null, operador, null, null, null));
        label.getStyleClass().add(Styles.ACCENT);
        flow.getChildren().add(label);
    }

    private void addValor(TextFlow flow, String valor) {
        Label label = new Label(valor);
        label.setUserData(new FiltroOrdemUserData(TipoFlow.VLR, null, null, valor, null, null));
        flow.getChildren().add(label);
    }

    private void addJuncao(TextFlow flow, Juncao juncao) {
        Label label = new Label(juncao.getLabel());
        label.setUserData(new FiltroOrdemUserData(TipoFlow.LIG, null, null, null, juncao, null));
        label.getStyleClass().add(Styles.DANGER);
        flow.getChildren().add(label);
    }

    private void addOrdem(TextFlow flow, ChoiceBox<Ordem> operadorChoiceBox) {
        Ordem ordem = operadorChoiceBox.getSelectionModel().getSelectedItem();
        Label label = new Label(ordem.getLabel());
        label.setUserData(new FiltroOrdemUserData(TipoFlow.ORD, null, null, null, null, ordem));
        label.getStyleClass().add(Styles.ACCENT);
        flow.getChildren().add(label);
    }

    public void aplicar() {
        List<FiltroOrdemUserData> filtroList = filtro.getChildren().stream().map(n -> (FiltroOrdemUserData) n.getUserData()).toList();
        List<FiltroOrdemUserData> ordemList = ordem.getChildren().stream().map(n -> (FiltroOrdemUserData) n.getUserData()).toList();

        if (!validaFiltro(filtroList)) {
            Components.alert(Alert.AlertType.ERROR, stage.getTitle(), "Filtro inválido.",
                    erroValidacao, false);
        } else {
            // aplicar filtro e ordenação e fechar
            stage.close();
        }
    }

    private boolean validaFiltro(List<FiltroOrdemUserData> operationList) {

        Deque<FiltroOrdemUserData> parentesesStack = new LinkedList<>();
        Deque<FiltroOrdemUserData> operacoesStack = new LinkedList<>();

        boolean ultimoEhJuncao = false;
        boolean juncaoEmParenteses = false;

        for (FiltroOrdemUserData token : operationList) {
            if (TipoFlow.COL.equals(token.tipo()) || TipoFlow.VLR.equals(token.tipo())) {
                continue;
            } else if (TipoFlow.LIG.equals(token.tipo())) {
                if (Juncao.OPEN.equals(token.ligacao())) {
                    if (!operacoesStack.isEmpty()) {
                        erroValidacao = "Verifique os parênteses abertos. Só é permitido abrir parênteses no início do filtro ou após um operador 'E' ou 'Ou'.";
                        return false;
                    }
                    parentesesStack.push(token);
                    juncaoEmParenteses = false;
                } else if (Juncao.CLOSE.equals(token.ligacao())) {
                    if (parentesesStack.isEmpty()) {
                        erroValidacao = "Verifique os parênteses. Há mais parênteses fechados do que abertos.";
                        return false; // Parenteses não correspondentes ou antes da hora
                    } else if (!juncaoEmParenteses) {
                        erroValidacao = "Verifique as operações dentro dos parênteses. Não é permitido usar parênteses sem um operador 'E' ou 'Ou' ocorrer dentro.";
                        return false; // Parenteses não correspondentes ou antes da hora
                    }
                    parentesesStack.pop();
                } else { //AND,OR
                    if (operacoesStack.isEmpty()) {
                        erroValidacao = "Verifique as operações de 'E' ou 'Ou'. Só é possível usá-las após uma comparação ou um fechamento de parênteses.";
                        return false; // Falta de operandos para o operador
                    }
                    operacoesStack.clear();
                    ultimoEhJuncao = true;
                    juncaoEmParenteses = !parentesesStack.isEmpty();
                }
            } else if (!operacoesStack.isEmpty()) {
                erroValidacao = "Verifique as comparações realizadas. Não é permitido realizar comparações consecutivas sem o uso de um operador 'E' ou 'Ou' para interligá-las.";
                return false; //Operações consecutivas
            } else {
                operacoesStack.push(token);
                ultimoEhJuncao = false;
            }
        }

        if (!parentesesStack.isEmpty()) {
            erroValidacao = "Verifique os parênteses. Há mais parênteses abertos do que fechados.";
            return false;
        }

        if (ultimoEhJuncao) {
            erroValidacao = "Verifique os operadores 'E' ou 'Ou'. Não é permitido finalizar o filtro com um operador.";
            return false;
        }

        return true;
    }
}

