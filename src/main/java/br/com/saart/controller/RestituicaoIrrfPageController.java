package br.com.saart.controller;

import br.com.saart.service.DirfService;
import br.com.saart.task.exportreport.ReportName;
import br.com.saart.view.controls.Components;
import br.com.saart.view.controls.TaskParamComponents;
import br.com.saart.view.relatorios.DeclaranteTable;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class RestituicaoIrrfPageController implements Initializable {

    public ChoiceBox<ReportName> tipoRelatorio;
    public ComboBox<DeclaranteTable> declarante;

    public GridPane parametrosGrid;

    @Autowired
    private DirfService dirfService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tipoRelatorio.setItems(FXCollections.observableList(ReportName.ofGroup("DIRF")));
        tipoRelatorio.getSelectionModel().select(0);

        declarante.setItems(FXCollections.observableList(dirfService.getDeclarantes()));
        Components.autoCompleteComboBox(declarante);

        TaskParamComponents.preencherGridParametros(parametrosGrid, tipoRelatorio.getSelectionModel().getSelectedItem().getParams(), 2);
    }

    //TODO alerta de dirf duplicada
    //TODO alerta de dirf faltando
    //TODO alerta de período anterior a 5 anos
    //TODO alerta de selic desatualizada
}
