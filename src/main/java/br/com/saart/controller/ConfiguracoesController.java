package br.com.saart.controller;

import br.com.saart.task.update.UpdateServiceTask;
import br.com.saart.util.UserPreferences;
import br.com.saart.view.StageFactory;
import br.com.saart.view.configuracoes.Temas;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import static br.com.saart.util.UserPreferences.Preference.TEMA;

@Controller
public class ConfiguracoesController implements Initializable {

    @Autowired
    private StageFactory stageFactory;

    @Autowired
    private UserPreferences preferences;

    @Autowired
    private ProgressController progressController;

    @Value("/view/configuracoes.fxml")
    private ClassPathResource scene;

    Stage stage;

    public ChoiceBox<Temas> temaChoiceBox;
    public Button fechar;
    public Label updateLabel;
    public Hyperlink updateLink;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        temaChoiceBox.setItems(FXCollections.observableArrayList(Temas.values()));
        temaChoiceBox.getSelectionModel().select(Temas.valueOf(preferences.get(TEMA)));

        fechar.setOnAction(e -> stage.close());
    }

    public void load() {
        if (stage == null) {
            stage = stageFactory.createStage(scene, "Configurações");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.toFront();
        }

        Platform.runLater(this::verificaAtualizacao);

        stage.showAndWait();
    }

    private void verificaAtualizacao() {
        final UpdateServiceTask updater = new UpdateServiceTask();
        stageFactory.getAw().autowireBean(updater);

        if (updater.temAtualizacao()) {
            updateLabel.setText(String.format("A versão %s do sistema já está disponível.", updater.getInfoDto().getVersao()));
            updateLabel.setGraphic(new FontIcon("mdoal-cloud_download"));
            updateLabel.getStyleClass().removeAll("success");

            updateLink.setOnAction(e -> {
                updater.startInNewThread(stageFactory.getAw(), progressController);
                stage.close();
            });
            updateLink.setVisible(true);
        } else {
            updateLabel.setText("Seu sistema está atualizado!");
            updateLabel.setGraphic(new FontIcon("mdal-check_circle_outline"));
            updateLabel.getStyleClass().add("success");

            updateLink.setVisible(false);
        }
    }

    public void trocaTema() {
        Temas tema = temaChoiceBox.getValue();
        Application.setUserAgentStylesheet(tema.getUserAgentStylesheet());
        preferences.set(TEMA, tema.name());
    }
}
