package br.com.saart.controller;

import atlantafx.base.theme.Theme;
import br.com.saart.JavafxApplication;
import br.com.saart.task.exportreport.ExportReportTask;
import br.com.saart.task.exportreport.ReportFormat;
import br.com.saart.task.exportreport.ReportName;
import br.com.saart.task.importdirf.ImportDirfTask;
import br.com.saart.util.UserPreferences;
import br.com.saart.util.UserPreferences.Preference;
import br.com.saart.view.Components;
import br.com.saart.view.StageFactory;
import br.com.saart.view.TaskParamComponents;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.ResourceBundle;

@Controller
public class PrimaryController implements Initializable {

    @Autowired
    private StageFactory stageFactory;
    @Autowired
    private JavafxApplication application;
    @Autowired
    private UserPreferences userPreferences;

    @Autowired
    private ProgressController progressController;
    @Value("/view/progress.fxml")
    private ClassPathResource progressScene;
    private Stage progressStage;

    //Abas
    public TabPane tabs;

    //Tab Importar DIRF
    public TextField impDirfInput;
    public Button impDirfInputButton;
    public ChoiceBox<Charset> impDirfInputCharset;
    public Button impDirfStartButton;

    //Tab Relatórios
    public ComboBox<ReportName> reportNames;
    public TextField reportOutput;
    public Button reportOutputButton;
    public ChoiceBox<ReportFormat> reportFormats;
    public Button reportStartButton;

    //Menu
    public MenuBar topMenu;
    public Menu menuTemas;

    @Override
    public void initialize(URL url, ResourceBundle resource) {

        //Init Enums
        impDirfInputCharset.setItems(FXCollections.observableList(Components.standardCharsets));
        reportNames.setItems(FXCollections.observableArrayList(ReportName.values()));
        reportFormats.setItems(FXCollections.observableArrayList(ReportFormat.values()));

        //Init User Saved Preferences
        loadSystemPreferences();
        loadImpDirfPreferences();
        loadReportPreferences();

        //Init Progress Bar
        progressStage = stageFactory.createStage(progressScene, "Progresso da Tarefa");
        progressStage.initModality(Modality.APPLICATION_MODAL);
        progressStage.setResizable(false);
    }

    public void impDirfInputButtonAction() {
        File selectedDir = Components
                .directoryChooser(impDirfInput.getText(), "Selecione a pasta de origem")
                .showDialog(impDirfInputButton.getScene().getWindow());
        if (selectedDir != null) {
            impDirfInput.setText(selectedDir.getAbsolutePath());
        }
    }

    public void reportOutputButtonAction() {
        File selectedDir = Components
                .directoryChooser(reportOutput.getText(), "Selecione a pasta de destino")
                .showDialog(reportOutputButton.getScene().getWindow());
        if (selectedDir != null) {
            reportOutput.setText(selectedDir.getAbsolutePath());
        }
    }

    @SneakyThrows
    public void impDirfStartButtonAction() {
        if (isValidImpDirfStart()) {
            saveImpDirfPreferences();

            ImportDirfTask task = new ImportDirfTask(impDirfInput.getText(), impDirfInputCharset.getValue());

            task.startInNewThread(getAw(), progressController);
        }
    }

    @SneakyThrows
    public void reportStartButtonAction() {
        if (isValidReportStart()) {
            Map<String, Object> params = TaskParamComponents.showParamDialog(reportNames.getValue().getParams());

            if (params != null) {
                saveReportPreferences();

                ExportReportTask task = new ExportReportTask(reportNames.getValue(), reportOutput.getText(), reportFormats.getValue(), params);

                task.startInNewThread(getAw(), progressController);
            }
        }
    }

    public void menuStop() {
        application.stop();
    }

    public void menuAbout() {
        Components.alert(AlertType.INFORMATION, "Sobre", "Versão " + stageFactory.getVersion(),
                "Desenvolvido por:\n\nJS TECNOLOGIA EM PROCESSAMENTO DE DADOS EIRELI.\njlucio@jsconsult.com.br", false);
    }

    public void menuTemas(ActionEvent e) {
        String tema = ((RadioMenuItem) e.getSource()).getId();
        changeTheme(tema);
        userPreferences.set(Preference.TEMA, tema);
    }

    private void loadSystemPreferences() {
        String tema = userPreferences.get(Preference.TEMA);
        changeTheme(tema);
        menuTemas.getItems().forEach(item -> {
            if (tema.equals(item.getId())) {
                ((RadioMenuItem) item).setSelected(true);
            }
        });
    }

    @SneakyThrows
    private void changeTheme(String tema) {
        Application.setUserAgentStylesheet("Java".equals(tema) ? null : ((Theme) Class.forName("atlantafx.base.theme." + tema).getDeclaredConstructor().newInstance()).getUserAgentStylesheet());
    }

    private void loadImpDirfPreferences() {
        impDirfInputCharset
                .setValue(Charset.forName(userPreferences.get(Preference.IMP_DIRF_INPUT_CHARSET)));
        impDirfInput.setText(userPreferences.get(Preference.IMP_DIRF_INPUT));
    }

    private void loadReportPreferences() {
        ReportName name = ReportName.valueOfOrNull(userPreferences.get(Preference.REPORT_NAME));
        if (name != null && reportNames.getItems().contains(name)) {
            reportNames.setValue(name);
        } else {
            reportNames.getSelectionModel().clearSelection();
        }
        reportOutput.setText(userPreferences.get(Preference.REPORT_OUTPUT));
        reportFormats.setValue(ReportFormat.valueOf(userPreferences.get(Preference.REPORT_FORMAT)));
    }

    private void saveImpDirfPreferences() {
        userPreferences.set(Preference.IMP_DIRF_INPUT_CHARSET, impDirfInputCharset.getValue().name());
        userPreferences.set(Preference.IMP_DIRF_INPUT, impDirfInput.getText());
    }

    private void saveReportPreferences() {
        userPreferences.set(Preference.REPORT_NAME, reportNames.getValue().name());
        userPreferences.set(Preference.REPORT_OUTPUT, reportOutput.getText());
        userPreferences.set(Preference.REPORT_FORMAT, reportFormats.getValue().name());
    }


    private boolean isValidImpDirfStart() {
        boolean valid =
                StringUtils.isNoneBlank(impDirfInput.getText()) && ObjectUtils
                        .allNotNull(impDirfInputCharset.getValue());

        if (!valid) {
            Components.alert(AlertType.ERROR, "Erro", "Não é possível iniciar",
                    "Todos os campos precisam ser preenchidos para iniciar a importação", false);
        }

        return valid;
    }

    private boolean isValidReportStart() {
        boolean valid = StringUtils.isNoneBlank(reportOutput.getText()) && ObjectUtils.allNotNull(
                reportNames.getValue(), reportFormats.getValue());

        if (!valid) {
            Components.alert(AlertType.ERROR, "Erro", "Não é possível iniciar",
                    "Todos os campos precisam ser preenchidos para gerar o relatório", false);
        }

        return valid;
    }

    private AutowireCapableBeanFactory getAw() {
        return stageFactory.getContext().getAutowireCapableBeanFactory();
    }

}
