package br.com.saart.controller;

import atlantafx.base.theme.Styles;
import br.com.saart.entity.Dirf;
import br.com.saart.repository.DirfRepository;
import br.com.saart.task.dirf.DeleteDirfTask;
import br.com.saart.task.dirf.ImportDirfTask;
import br.com.saart.util.UserPreferences;
import br.com.saart.util.Util;
import br.com.saart.view.StageFactory;
import br.com.saart.view.controls.Components;
import br.com.saart.view.dirf.DirfTable;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

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
    private DirfRepository dirfRepository;

    public SplitMenuButton importar;
    public TableView<DirfTable> tabela;
    public Pagination paginacao;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tabela.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tabela.getColumns().forEach(coluna -> Components.configuraColuna(Dirf.class, tabela, (TableColumn<DirfTable, String>) coluna, coluna.getId()));
        paginacao.setPageFactory(pageIndex -> {
            lerPagina(pageIndex, PAGE_SIZE);
            return new Region();
        });
        Platform.runLater(() -> lerPagina(0, PAGE_SIZE));
    }

    public void importar() {
        File selectedDir = Components
                .directoryChooser(preferences.get(UserPreferences.Preference.IMP_DIRF_INPUT), "Selecione a pasta de origem")
                .showDialog(importar.getScene().getWindow());

        if (selectedDir != null) {
            preferences.set(UserPreferences.Preference.IMP_DIRF_INPUT, selectedDir.getAbsolutePath());
            ImportDirfTask task = new ImportDirfTask(selectedDir.getAbsolutePath());
            task.startInNewThread(stageFactory.getAw(), progressController);
            progressController.setFinishedAction(() -> lerPagina(paginacao.getCurrentPageIndex(), PAGE_SIZE));
        }
    }

    public void excluir() {
        ObservableList<DirfTable> selectedItems = tabela.getSelectionModel().getSelectedItems();

        if (CollectionUtils.isEmpty(selectedItems)) {
            Components.alert(Alert.AlertType.WARNING, "Exclusão de registros", "Nenhum registro foi selecionado para exclusão.", "É preciso selecionar um ou mais registros para excluir.", false);
        } else if (Components.alert(Alert.AlertType.CONFIRMATION, "Exclusão de registros", String.format("Tem certeza que deseja excluir o(s) %d registro(s) selecionado(s)?", selectedItems.size()),
                "A exclusão é permanente e estes registros não poderão ser recuperados.", true, Styles.DANGER).orElse(ButtonType.NO).equals(ButtonType.OK)) {
            DeleteDirfTask deleteDirfTask = new DeleteDirfTask(selectedItems);
            deleteDirfTask.startInNewThread(stageFactory.getAw(), progressController);
            progressController.setFinishedAction(() -> lerPagina(paginacao.getCurrentPageIndex(), PAGE_SIZE));
        }
    }

    private void lerPagina(int pageNumber, int pageSize) {
        Page<DirfTable> pagina = dirfRepository.findAll(Pageable.ofSize(pageSize).withPage(pageNumber)).map(this::entityToTable);
        tabela.setItems(FXCollections.observableList(pagina.toList()));

        paginacao.setCurrentPageIndex(pageNumber);
        paginacao.setPageCount(pagina.getTotalPages());
    }

    private DirfTable entityToTable(Dirf dirf) {
        DirfTable result = new DirfTable();
        result.setId(dirf.getId());
        result.setNome(dirf.getDeclarante().getNome());
        result.setCpfCnpj(dirf.getDeclarante().getCpfCnpj());
        result.setRetificadora(dirf.getRetificadora() ? "S" : "N");
        result.setImportadoEm(Util.toDMY(dirf.getImportadoEm().toLocalDate()) + " " + Util.toHMS(dirf.getImportadoEm().toLocalTime()));
        result.setAnoReferencia(dirf.getAnoReferencia().toString());
        result.setAnoCalendario(dirf.getAnoCalendario().toString());
        result.setNomeArquivo(dirf.getNomeArquivo());
        return result;
    }
}
