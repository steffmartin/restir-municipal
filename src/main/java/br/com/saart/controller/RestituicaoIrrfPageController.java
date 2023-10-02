package br.com.saart.controller;

import atlantafx.base.controls.ToggleSwitch;
import br.com.saart.service.DirfService;
import br.com.saart.task.exportreport.ExportReportTask;
import br.com.saart.task.exportreport.ReportFormat;
import br.com.saart.task.exportreport.ReportName;
import br.com.saart.view.StageFactory;
import br.com.saart.view.controls.Components;
import br.com.saart.view.controls.TaskParamComponents;
import br.com.saart.view.relatorios.DeclaranteTable;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import lombok.SneakyThrows;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;


@Controller
public class RestituicaoIrrfPageController implements Initializable {

    public ChoiceBox<ReportName> tipoRelatorio;
    public ComboBox<DeclaranteTable> declarante;

    public GridPane parametrosGrid;

    @Autowired
    private DirfService dirfService;

    @Autowired
    private StageFactory stageFactory;

    @Autowired
    private ProgressController progressController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<ReportName> reportNames = ReportName.ofGroup("DIRF");
        tipoRelatorio.setItems(FXCollections.observableList(reportNames));
        TaskParamComponents.preencherGridParametros(parametrosGrid, reportNames.get(0).getParams(), 2);

        declarante.setItems(FXCollections.observableList(dirfService.getDeclarantes()));
        Components.autoCompleteComboBox(declarante);
    }

    //TODO alerta de dirf duplicada
    //TODO alerta de dirf faltando
    //TODO alerta de período anterior a 5 anos
    //TODO alerta de selic desatualizada

    @SneakyThrows
    public void reportStartButtonAction(ActionEvent e) {
        ReportFormat reportFormat = ReportFormat.valueOf(e.getTarget() instanceof SplitMenuButton btn ? btn.getId() : ((MenuItem) e.getTarget()).getId());

        if (isValidReportStart()) {
            File file = generateFile(reportFormat);

            if (file != null) {
                Map<String, Object> params = TaskParamComponents.converterGridParametros(parametrosGrid, 2);
                params.put("CNPJ_DECLARANTE", declarante.getValue().getCpfCnpj());
                params.put("NOME_DECLARANTE", declarante.getValue().getNome());

                ExportReportTask task = new ExportReportTask(tipoRelatorio.getValue(), file, reportFormat, params);
                task.startInNewThread(stageFactory.getAw(), progressController);
            }
        }
    }

    private File generateFile(ReportFormat reportFormat) {
        File file = Components.fileChooser(System.getProperty("user.home") + "\\relatorio." + reportFormat.getExtension(), "Salvar como",
                reportFormat.getDescription(), reportFormat.getExtension()).showSaveDialog(parametrosGrid.getScene().getWindow());
        if (file != null && !StringUtils.endsWithIgnoreCase(file.getAbsolutePath(), reportFormat.getExtension())) {
            file = new File(file.getAbsolutePath() + "." + reportFormat.getExtension());
        }
        return file;
    }

    private boolean isValidReportStart() {
        boolean valid = ObjectUtils.allNotNull(tipoRelatorio.getValue(), declarante.getValue());

        if (!valid) {
            Components.alert(Alert.AlertType.ERROR, "Erro", "Não é possível continuar",
                    "Há parâmetros obrigatórios não preenchidos ou parâmetros preenchidos incorretamente, verifique.", false);
        }

        return valid && TaskParamComponents.isValidTaskParams(parametrosGrid, 2);
    }
}
