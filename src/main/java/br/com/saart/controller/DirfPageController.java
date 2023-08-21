package br.com.saart.controller;

import br.com.saart.task.importdirf.ImportDirfTask;
import br.com.saart.util.UserPreferences;
import br.com.saart.view.StageFactory;
import br.com.saart.view.controls.Components;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitMenuButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class DirfPageController implements Initializable {

    @Autowired
    private UserPreferences preferences;
    @Autowired
    private StageFactory stageFactory;
    @Autowired
    private ProgressController progressController;

    public SplitMenuButton importar;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void importar() {
        File selectedDir = Components
                .directoryChooser(preferences.get(UserPreferences.Preference.IMP_DIRF_INPUT), "Selecione a pasta de origem")
                .showDialog(importar.getScene().getWindow());

        if (selectedDir != null) {
            preferences.set(UserPreferences.Preference.IMP_DIRF_INPUT, selectedDir.getAbsolutePath());
            ImportDirfTask task = new ImportDirfTask(selectedDir.getAbsolutePath());
            task.startInNewThread(stageFactory.getAw(), progressController);
        }
    }
}
