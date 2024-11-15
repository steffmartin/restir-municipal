package br.com.saart.controller;

import br.com.saart.util.Util;
import br.com.saart.view.StageFactory;
import br.com.saart.view.controls.Components;
import br.com.saart.view.progress.ProgressFinishedAction;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class ProgressController implements Initializable {

    @Autowired
    private StageFactory stageFactory;

    @Value("/view/progress.fxml")
    private ClassPathResource progressScene;

    @Setter
    private ProgressFinishedAction finishedAction;

    Stage stage;

    public ProgressBar progressBar;
    public Label progressPercent;
    public Label progressMessage;
    public Button closeButton;
    public Button errorButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        progressBar.progressProperty().addListener((obs, oldProgress, newProgress) ->
        {
            int intProgress = (int) (newProgress.doubleValue() * 100);
            progressPercent.setText(String.format("%d %%", intProgress));
            if (intProgress > 50) {
                progressPercent.setTextFill(Color.WHITE);
            } else {
                progressPercent.setTextFill(Color.BLACK);
            }
        });
    }

    public void montar() {
        if (stage == null) {
            stage = stageFactory.createStage(progressScene, "Progresso");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
        }
    }

    public void abrir(ReadOnlyDoubleProperty progress, ReadOnlyStringProperty message) {
        progressBar.progressProperty().bind(progress);
        progressMessage.textProperty().bind(message);
        progressPercent.setText("");

        disableClosing();
        errorButton.setVisible(false);
        stage.show();
    }

    public void finalizar(Map<String, Throwable> errors) {
        enableClosing();
        errorButton.setUserData(errors);
        errorButton.setVisible(!errors.isEmpty());
    }

    public void falhar(Throwable e) {
        Components.exception(e);
        close();
    }

    private void disableClosing() {
        closeButton.setDisable(true);
        stage.setOnCloseRequest(Event::consume);
    }

    private void enableClosing() {
        closeButton.setDisable(false);
        stage.setOnCloseRequest(e -> close());
    }

    public void close() {
        stage.close();

        if (finishedAction != null) {
            finishedAction.execute();
            finishedAction = null;
        }
    }

    @SuppressWarnings("unchecked")
    public void showErrors() {
        Map<String, Throwable> errors = (Map<String, Throwable>) errorButton.getUserData();
        Components.exception("Os arquivos abaixo causaram erro e não foram considerados.",
                StringUtils.joinWith(Util.EOL, errors.keySet().toArray()), parseErrorsToString(errors));
    }

    private String parseErrorsToString(Map<String, Throwable> errors) {
        return errors.entrySet().stream().map(erro -> StringUtils.joinWith(Util.EOL,
                erro.getKey(),
                StringUtils.firstNonBlank(erro.getValue().getMessage(), "Erro inesperado"),
                ExceptionUtils.getStackTrace(erro.getValue()))
        ).collect(Collectors.joining(Util.EOL + Util.EOL + Util.EOL));
    }


}
