package br.com.saart.view;

import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ApplicationPreLoader extends Preloader {

    private Stage stage;

    @Override
    public void start(Stage stage) {
        Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());

        this.stage = stage;

        VBox v = new VBox(10, new ImageView("view/img/ico.png"), new Label("Iniciando..."));
        v.setAlignment(Pos.CENTER);

        BorderPane p = new BorderPane(v);

        stage.setScene(new Scene(p, 200, 200));
        stage.initStyle(StageStyle.UNDECORATED);

        stage.show();
    }

    @Override
    public boolean handleErrorNotification(ErrorNotification info) {
        stage.hide();
        Components.exception(info.getCause());
        return false;
    }

    @Override
    public void handleApplicationNotification(PreloaderNotification info) {
        if (info instanceof ProgressNotification pn && 100D == pn.getProgress()) {
            stage.hide();
        }
    }
}
