package br.com.saart.view;

import javafx.application.Preloader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ApplicationPreLoader extends Preloader {

    private Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;

        VBox v = new VBox(10, new ProgressBar(), new Label("Iniciando..."));
        v.setAlignment(Pos.CENTER);

        BorderPane p = new BorderPane(v);

        stage.setScene(new Scene(p, 200, 100));
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
        if (info instanceof ProgressNotification && 100D == ((ProgressNotification) info)
                .getProgress()) {
            stage.hide();
        }
    }
}
