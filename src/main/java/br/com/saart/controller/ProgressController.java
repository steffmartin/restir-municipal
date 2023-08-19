package br.com.saart.controller;

import br.com.saart.JavafxApplication;
import br.com.saart.util.Util;
import br.com.saart.view.Components;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class ProgressController implements Initializable {

    @Autowired
    private JavafxApplication application;

    @Value("${spring.application.install-dir}")
    private String installDir;

    public ProgressBar progressBar;
    public Label progressMessage;
    public Button closeButton;
    public Button errorButton;
    public boolean needRestart = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void onScheduled(ReadOnlyDoubleProperty progress, ReadOnlyStringProperty message) {
        progressBar.progressProperty().bind(progress);
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

        if (needRestart) {
            closeButton.setOnAction(this::restart);
            closeButton.getScene().getWindow().setOnCloseRequest(this::restart);
        }
    }

    private void restart(Event e) {
        try {
            //Executa o CMD para abrir o atalho 'restart' da pasta de instalação do APP, este atalho executa um comando para finalizar a atualização
            //Pois o comando final não pode ser executado por aqui porque o APP precisa estar fechado para que o JAR da versão antiga seja trocado pelo novo
            new ProcessBuilder("cmd.exe", "/c", "start", "/D", installDir, "restart.lnk").start().waitFor();
        } catch (Exception ex) {
            log.error("Erro ao iniciar o processo", ex);
        }
        e.consume();

        //Fecha o APP para que o comando consiga apagar este JAR
        application.stop();
    }

    private String parseErrorsToString(Map<String, Throwable> errors) {
        return errors.entrySet().stream().map(erro -> StringUtils.joinWith(Util.EOL,
                erro.getKey(),
                StringUtils.firstNonBlank(erro.getValue().getMessage(), "Erro inesperado"),
                ExceptionUtils.getStackTrace(erro.getValue()))
        ).collect(Collectors.joining(Util.EOL + Util.EOL + Util.EOL));
    }


}
