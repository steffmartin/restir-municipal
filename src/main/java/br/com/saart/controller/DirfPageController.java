package br.com.saart.controller;

import atlantafx.base.theme.Styles;
import br.com.saart.entity.dirf.Dirf;
import br.com.saart.service.DirfService;
import br.com.saart.specification.CustomSpecification;
import br.com.saart.task.dirf.DeleteDirfTask;
import br.com.saart.task.dirf.ImportDirfTask;
import br.com.saart.util.UserPreferences;
import br.com.saart.view.StageFactory;
import br.com.saart.view.controls.Components;
import br.com.saart.view.dirf.DirfTable;
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

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static br.com.saart.view.controls.Components.alert;
import static br.com.saart.view.controls.Components.configuraColuna;
import static javafx.scene.control.Alert.AlertType.CONFIRMATION;
import static javafx.scene.control.Alert.AlertType.INFORMATION;

@Controller
public class DirfPageController implements Initializable {

    private static final int PAGE_SIZE = 30;

    @Autowired
    private UserPreferences preferences;

    @Autowired
    private StageFactory stageFactory;

    @Autowired
    private ProgressController progressController;

    @Autowired
    private FiltrosController filtrosController;

    @Autowired
    private DirfService dirfService;

    @Autowired
    private CustomSpecification<Dirf> dirfCustomSpecification;

    public SplitMenuButton importar;
    public MenuItem excluir;
    public TableView<DirfTable> tabela;
    public Pagination paginacao;

    private Specification<Dirf> currentSpecification = null;
    private Sort currentSort = Sort.unsorted();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        excluir.disableProperty().bind(Bindings.isNotEmpty(tabela.getSelectionModel().getSelectedIndices()).not());
        tabela.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tabela.getColumns().forEach(coluna -> configuraColuna(Dirf.class, tabela, (TableColumn<DirfTable, String>) coluna, coluna.getId()));
        paginacao.setPageFactory(i -> lerPagina());
        Platform.runLater(this::lerPagina);
    }

    public void abrirFiltros() {
        filtrosController.montar();

        filtrosController.abrir(DirfTable.COLUNAS, dirfCustomSpecification, (specification, sort) -> {
            currentSpecification = (Specification<Dirf>) specification;
            currentSort = sort;
            lerPagina();
        });
    }

    private Node lerPagina() {
        Pageable pageable = PageRequest.of(paginacao.getCurrentPageIndex(), PAGE_SIZE, currentSort);

        Page<DirfTable> pagina = dirfService.findAll(pageable, currentSpecification).map(DirfTable::new);

        tabela.setItems(FXCollections.observableList(pagina.toList()));

        paginacao.setCurrentPageIndex(pagina.getNumber());
        paginacao.setPageCount(pagina.getTotalPages());

        return new Region();
    }

    public void importar() {

        String formatos = Arrays.stream(ImportDirfTask.EXTENSOES_SUPORTADAS).map(String::toUpperCase)
                .distinct().map("*."::concat).collect(Collectors.joining(" "));
        Integer menorAno = ImportDirfTask.LEIAUTES_SUPORTADOS.values().stream().mapToInt(Integer::intValue).min().getAsInt();
        Integer maiorAno = ImportDirfTask.LEIAUTES_SUPORTADOS.values().stream().mapToInt(Integer::intValue).max().getAsInt();

        alert(INFORMATION, "Importação de registros", "Selecione uma pasta contendo uma ou mais declarações.",
                String.format("O sistema suporta declarações com leiautes dos anos de %d a %d nos seguintes formatos: %s.", menorAno, maiorAno, formatos), true);

        File selectedDir = Components
                .directoryChooser(preferences.get(UserPreferences.Preference.IMP_DIRF_INPUT), "Selecione a pasta de origem")
                .showDialog(importar.getScene().getWindow());

        if (selectedDir != null) {
            preferences.set(UserPreferences.Preference.IMP_DIRF_INPUT, selectedDir.getAbsolutePath());
            progressController.setFinishedAction(this::lerPagina);
            ImportDirfTask task = new ImportDirfTask(selectedDir.getAbsolutePath());
            task.startInNewThread(stageFactory.getAw(), progressController);
        }
    }

    public void excluir() {
        ObservableList<DirfTable> selectedItems = tabela.getSelectionModel().getSelectedItems();

        if (confirmarExclusao(selectedItems.size())) {
            progressController.setFinishedAction(this::lerPagina);
            DeleteDirfTask deleteDirfTask = new DeleteDirfTask(selectedItems);
            deleteDirfTask.startInNewThread(stageFactory.getAw(), progressController);
        }
    }

    private boolean confirmarExclusao(int quantidade) {
        return ButtonType.OK.equals(alert(CONFIRMATION, "Exclusão de registros",
                String.format("Tem certeza que deseja excluir o(s) %d registro(s) selecionado(s)?", quantidade),
                "A exclusão é permanente e estes registros não poderão ser recuperados.", true, Styles.DANGER)
                .orElse(ButtonType.NO));
    }

}
