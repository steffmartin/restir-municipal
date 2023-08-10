package br.com.smaconsulting.saart.task.importdirf;

import br.com.smaconsulting.saart.task.GenericTask;
import br.com.smaconsulting.saart.util.Util;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

import java.io.BufferedReader;
import java.io.File;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
public class ImportDirfTask extends GenericTask {

    //Inputs
    private final String inputDir;
    private final Charset inputCharset;

    //Service
    @Autowired
//  private DirfService dirfService;

    @Override
    protected Map<String, Throwable> call() {
        updateMessage("Iniciando...");
        StopWatch watch = new StopWatch();
        watch.start();

        updateMessage("Calculando total de DIRFs...");
        List<String> fileNames = fileNames();

        int progress = 0;
        for (String fileName : fileNames) {
            updateMessage("Importando " + fileName);
            importDirf(fileName);
            updateProgress(++progress, fileNames.size());
        }

        watch.stop();
        if (progress == 0) {
            updateProgress(1, 1);
            updateMessage("Não há arquivos para importar.");
        } else {
            updateMessage("Tarefa finalizada! Tempo gasto em " + progress + " DIRFs: " + Util.toHMS(
                    watch.getTotalTimeMillis()));
        }

        return errors;
    }

    private void importDirf(String fileName) {
        try (BufferedReader dirfFile = Util.getReader(inputDir + fileName, inputCharset)) {
            //TODO importação do arquivo
        } catch (Exception e) {
            e.printStackTrace();
            errors.put(fileName, e);
        }
    }

    private List<String> fileNames() {
        File inputPath = new File(inputDir);

        if (inputPath.isDirectory()) {
            return FileUtils.listFiles(inputPath, new String[]{"txt", "TXT"}, true)
                    .stream().map(file -> StringUtils.removeStart(file.getAbsolutePath(), inputDir))
                    .sorted().collect(toList());
        } else {
            return Collections.emptyList();
        }
    }
}
