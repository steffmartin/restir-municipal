package br.com.saart.task.dirf;

import br.com.saart.service.DirfService;
import br.com.saart.task.GenericTask;
import br.com.saart.util.Util;
import br.com.saart.view.dirf.DirfTable;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.Map;

public class DeleteDirfTask extends GenericTask {

    @Autowired
    private DirfService dirfService;

    //Inputs
    private final List<DirfTable> selectedItems;

    public DeleteDirfTask(ObservableList<DirfTable> selectedItems) {
        this.selectedItems = selectedItems.stream().toList();
    }

    @Override
    protected Map<String, Throwable> call() throws Exception {
        updateMessage("Iniciando...");
        StopWatch watch = new StopWatch();
        watch.start();

        int progress = 0;
        for (DirfTable dirf : selectedItems) {
            updateMessage("Excluindo " + dirf.getNomeArquivo());
            excluiDirf(dirf);
            updateProgress(++progress, selectedItems.size());
        }

        watch.stop();
        if (progress == 0) {
            updateProgress(1, 1);
            updateMessage("Não há registros para excluir.");
        } else {
            updateMessage("Registros excluídos! Tempo gasto em " + progress + " DIRFs: " + Util.toHMS(watch.getTotalTimeMillis()));
        }

        return errors;
    }

    private void excluiDirf(DirfTable dirf) {
        try {
            dirfService.delete(Long.parseLong(dirf.getDirfId()));
        } catch (Exception e) {
            e.printStackTrace();
            errors.put(dirf.getNomeArquivo(), e);
        }
    }
}
