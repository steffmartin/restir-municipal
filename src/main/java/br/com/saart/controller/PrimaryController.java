package br.com.saart.controller;

import br.com.saart.JavafxApplication;
import br.com.saart.task.exportreport.ReportFormat;
import br.com.saart.task.exportreport.ReportName;
import br.com.saart.task.importdirf.ImportDirfTask;
import br.com.saart.util.Components;
import br.com.saart.util.UserPreferences;
import br.com.saart.util.UserPreferences.Preference;
import br.com.saart.view.StageFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

@Deprecated
public class PrimaryController {

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

    public void initialize(URL url, ResourceBundle resource) {

        loadImpDirfPreferences();
    }

    public void impDirfInputButtonAction() {
        File selectedDir = Components
                .directoryChooser(impDirfInput.getText(), "Selecione a pasta de origem")
                .showDialog(impDirfInputButton.getScene().getWindow());
        if (selectedDir != null) {
            impDirfInput.setText(selectedDir.getAbsolutePath());
        }
    }


    @SneakyThrows
    public void impDirfStartButtonAction() {
        if (isValidImpDirfStart()) {
            saveImpDirfPreferences();

            ImportDirfTask task = new ImportDirfTask(impDirfInput.getText());

            task.startInNewThread(getAw(), progressController);
        }
    }


    public void menuAbout() {
        Components.alert(AlertType.INFORMATION, "Sobre", "Versão " + stageFactory.getVersion(),
                "Desenvolvido por:\n\nJS TECNOLOGIA EM PROCESSAMENTO DE DADOS EIRELI.\njlucio@jsconsult.com.br", false);
    }


    private void loadImpDirfPreferences() {
        impDirfInput.setText(userPreferences.get(Preference.IMP_DIRF_INPUT));
    }


    private void saveImpDirfPreferences() {
        userPreferences.set(Preference.IMP_DIRF_INPUT, impDirfInput.getText());
    }


    private boolean isValidImpDirfStart() {
        boolean valid = StringUtils.isNoneBlank(impDirfInput.getText());

        if (!valid) {
            Components.alert(AlertType.ERROR, "Erro", "Não é possível iniciar",
                    "Todos os campos precisam ser preenchidos para iniciar a importação", false);
        }

        return valid;
    }

    private AutowireCapableBeanFactory getAw() {
        return stageFactory.getContext().getAutowireCapableBeanFactory();
    }

}
