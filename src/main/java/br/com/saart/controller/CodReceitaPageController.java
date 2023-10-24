package br.com.saart.controller;

import atlantafx.base.theme.Styles;
import br.com.saart.JavafxApplication;
import br.com.saart.entity.codRec.CodReceita;
import br.com.saart.service.CodRecService;
import br.com.saart.specification.CustomSpecification;
import br.com.saart.view.codRec.CodRecTable;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import static br.com.saart.view.controls.Components.alert;
import static br.com.saart.view.controls.Components.configuraColuna;
import static javafx.scene.control.Alert.AlertType.CONFIRMATION;
import static javafx.scene.control.Alert.AlertType.WARNING;

@Controller
public class CodReceitaPageController implements Initializable {

    private static final int PAGE_SIZE = 30;

    @Autowired
    private FiltrosController filtrosController;

    @Autowired
    private CodRecService codRecService;

    @Autowired
    private CustomSpecification<CodReceita> codReceitaCustomSpecification;

    @Autowired
    private JavafxApplication application;

    public Hyperlink linkReceita;
    public SplitMenuButton adicionar;
    public MenuItem alterar;
    public MenuItem excluir;
    public TableView<CodRecTable> tabela;
    public Pagination paginacao;

    private Specification<CodReceita> currentSpecification = null;
    private Sort currentSort = Sort.unsorted();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alterar.disableProperty().bind(Bindings.size(tabela.getSelectionModel().getSelectedIndices()).isNotEqualTo(1));
        excluir.disableProperty().bind(Bindings.isNotEmpty(tabela.getSelectionModel().getSelectedIndices()).not());
        tabela.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tabela.getColumns().forEach(coluna -> configuraColuna(CodReceita.class, tabela, (TableColumn<CodRecTable, String>) coluna, coluna.getId()));
        paginacao.setPageFactory(i -> lerPagina());
        Platform.runLater(this::lerPagina);
    }

    private Node lerPagina() {
        Pageable pageable = PageRequest.of(paginacao.getCurrentPageIndex(), PAGE_SIZE, currentSort);

        Page<CodRecTable> pagina = codRecService.findAll(pageable, currentSpecification).map(CodRecTable::new);

        tabela.setItems(FXCollections.observableList(pagina.toList()));

        paginacao.setCurrentPageIndex(pagina.getNumber());
        paginacao.setPageCount(pagina.getTotalPages());

        return new Region();
    }

    public void abrirFiltros() {
        filtrosController.montar();

        filtrosController.abrir(CodRecTable.COLUNAS, codReceitaCustomSpecification, (specification, sort) -> {
            currentSpecification = (Specification<CodReceita>) specification;
            currentSort = sort;
            lerPagina();
        });
    }

    public void adicionar() {
        alert(WARNING, "Em desenvolvimento", "Esta funcionalidade está sendo desenvolvida", "A inclusão de registros na tabela de códigos de Receita será adicionada na próxima versão do sistema.", false);
    }

    public void alterar() {
        alert(WARNING, "Em desenvolvimento", "Esta funcionalidade está sendo desenvolvida", "A alteração de registros da tabela de códigos de Receita será adicionada na próxima versão do sistema.", false);
    }

    public void excluir() {
        ObservableList<CodRecTable> selectedItems = tabela.getSelectionModel().getSelectedItems();

        if (confirmarExclusao(selectedItems.size())) {
            selectedItems.forEach(linha -> codRecService.delete(linha.getIdCodRec()));
            lerPagina();
        }
    }

    private boolean confirmarExclusao(int quantidade) {
        return ButtonType.OK.equals(alert(CONFIRMATION, "Exclusão de registros",
                String.format("Tem certeza que deseja excluir o(s) %d registro(s) selecionado(s)?", quantidade),
                "A exclusão é permanente e estes registros não poderão ser recuperados.", true, Styles.DANGER)
                .orElse(ButtonType.NO));
    }

    public void abrirReceita() {
        application.getHostServices().showDocument("https://siefreceitas.receita.economia.gov.br/codigos-de-receita-de-tributos-e-contribuicoes-darf-e-dje");
    }
}
