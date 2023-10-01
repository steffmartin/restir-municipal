package br.com.saart.controller;

import atlantafx.base.controls.Message;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

@Controller
public class RestituicaoIrrfPageController implements Initializable {

    public VBox msgBox;
    public Message msgSelic;
    public Message msgDirfDuplicada;
    public Message msgDirfAusente;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        msgBox.getChildren().clear();
    }
}
