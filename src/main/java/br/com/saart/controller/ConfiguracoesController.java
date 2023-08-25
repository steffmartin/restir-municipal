package br.com.saart.controller;

import br.com.saart.JavafxApplication;
import br.com.saart.service.UpdateService;
import br.com.saart.task.update.UpdateTask;
import br.com.saart.util.UserPreferences;
import br.com.saart.view.StageFactory;
import br.com.saart.view.configuracoes.Temas;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.kordamp.ikonli.javafx.FontIcon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import static br.com.saart.util.UserPreferences.Preference.TEMA;

@Slf4j
@Controller
public class ConfiguracoesController implements Initializable {

    @Autowired
    private JavafxApplication application;

    @Autowired
    private StageFactory stageFactory;

    @Autowired
    private UserPreferences preferences;

    @Autowired
    private ProgressController progressController;

    @Autowired
    private UpdateService updateService;

    @Value("/view/configuracoes.fxml")
    private ClassPathResource scene;

    @Value("${spring.application.install-dir}")
    private String installDir;

    Stage stage;

    public ChoiceBox<Temas> temaChoiceBox;
    public Label updateLabel;
    public Hyperlink updateLink;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        temaChoiceBox.setItems(FXCollections.observableArrayList(Temas.values()));
        temaChoiceBox.getSelectionModel().select(Temas.valueOf(preferences.get(TEMA)));
    }

    public void montar() {
        if (stage == null) {
            stage = stageFactory.createStage(scene, "Configurações");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.toFront();
            stage.setOnCloseRequest(e -> fechar());
        }
    }

    public void abrir() {
        Platform.runLater(this::checkUpdates);
        stage.showAndWait();
    }

    public void fechar() {
        progressController.setFinishedAction(null);
        stage.close();
    }

    public void trocaTema() {
        Temas tema = temaChoiceBox.getValue();
        Application.setUserAgentStylesheet(tema.getUserAgentStylesheet());
        preferences.set(TEMA, tema.name());
    }

    private void checkUpdates() {
        if (updateService.temAtualizacao()) {

            progressController.setFinishedAction(() -> {
                try {
                    new ProcessBuilder("cmd.exe", "/c", "start", "/D", installDir, "restart.lnk").start().waitFor();
                } catch (Exception ex) {
                    log.error("Erro ao iniciar o processo", ex);
                } finally {
                    application.stop();
                }
            });

            updateLink.setOnAction(e -> {
                stage.close();
                UpdateTask updateTask = new UpdateTask();
                updateTask.startInNewThread(stageFactory.getAw(), progressController);
            });

            updateLabel.setText(String.format("A versão %s do sistema já está disponível.", updateService.getUltimaBusca().getVersao()));
            updateLabel.setGraphic(new FontIcon("mdoal-cloud_download"));
            updateLabel.getStyleClass().removeAll("success");
            updateLink.setVisible(true);
        } else {
            updateLabel.setText("Seu sistema está atualizado!");
            updateLabel.setGraphic(new FontIcon("mdal-check_circle_outline"));
            updateLabel.getStyleClass().add("success");
            updateLink.setVisible(false);
        }
    }
}
