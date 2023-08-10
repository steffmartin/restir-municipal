package br.com.smaconsulting.saart.controller;

import br.com.smaconsulting.saart.util.Util;
import br.com.smaconsulting.saart.view.Components;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Controller
public class ProgressController implements Initializable {

    public ProgressBar progressBar;
    public ProgressIndicator progressIndicator;
    public Label progressMessage;
    public Button closeButton;
    public Button errorButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void onScheduled(ReadOnlyDoubleProperty progress, ReadOnlyStringProperty message) {
        progressBar.progressProperty().bind(progress);
        progressIndicator.progressProperty().bind(progress);
        progressMessage.textProperty().bind(message);

        disableClosing();
        errorButton.setVisible(false);
        ((Stage) closeButton.getScene().getWindow()).show();
    }

    public void onSucceeded(Map<String, Throwable> errors) {
        enableClosing();
        errorButton.setUserData(errors);
        errorButton.setVisible(!errors.isEmpty());
    }

    public void onFailed(Throwable e) {
        Components.exception(e);
        close();
    }

    public void close() {
        closeButton.getScene().getWindow().hide();
    }

    @SuppressWarnings("unchecked")
    public void showErrors() {
        Map<String, Throwable> errors = (Map<String, Throwable>) errorButton.getUserData();
        Components.exception("Os arquivos abaixo causaram erro e não foram considerados.",
                StringUtils.joinWith(Util.EOL, errors.keySet().toArray()), parseErrorsToString(errors));
    }

    private void disableClosing() {
        closeButton.setDisable(true);
        closeButton.getScene().getWindow().setOnCloseRequest(Event::consume);
    }

    private void enableClosing() {
        closeButton.setDisable(false);
        closeButton.getScene().getWindow().setOnCloseRequest(null);
    }

    private String parseErrorsToString(Map<String, Throwable> errors) {
        return errors.entrySet().stream().map(erro -> StringUtils.joinWith(Util.EOL,
                erro.getKey(),
                StringUtils.firstNonBlank(erro.getValue().getMessage(), "Erro inesperado"),
                ExceptionUtils.getStackTrace(erro.getValue()))
        ).collect(Collectors.joining(Util.EOL + Util.EOL + Util.EOL));
    }


}
