package br.com.saart.task.importdirf;

import br.com.saart.entity.*;
import br.com.saart.repository.DirfRepository;
import br.com.saart.task.GenericTask;
import br.com.saart.util.Util;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
public class ImportDirfTask extends GenericTask {

    private static final List<String> leiautesSuportados = new ArrayList<>();

    static {
        leiautesSuportados.add("ARNZRXP");
    }

    //Inputs
    private final String inputDir;
    private final Charset inputCharset;

    //Repository
    @Autowired
    private DirfRepository dirfRepository;

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

            validaArquivoDirf(dirfFile);

            Dirf dirf = new Dirf(fileName);
            int numLinha = 0;

            //Último
            Integer idRecLinha = null;
            String idRecCod = null;
            Beneficiario beneficiario = null;

            for (String[] campos : dirfFile.lines().map("|"::concat).map(Util::split).collect(toList())) {
                numLinha++;

                switch (campos[1].toUpperCase()) {
                    case "DIRF": {
                        dirf.initDirf(numLinha, campos);
                        break;
                    }
                    case "RESPO": {
                        dirf.setResponsavel(new Responsavel(numLinha, campos));
                        break;
                    }
                    case "DECPF":
                    case "DECPJ": {
                        dirf.setDeclarante(new Declarante(numLinha, campos));
                        break;
                    }
                    case "IDREC": {
                        idRecLinha = numLinha;
                        idRecCod = campos[2];
                        break;
                    }
                    case "BPFDEC":
                    case "BPJDEC":
                    case "VPEIM": {
                        beneficiario = new Beneficiario(numLinha, campos);
                        dirf.getDeclarante().getBeneficiarios().add(beneficiario);
                        break;
                    }
                    case "FIMDIRF": {
                        dirf.setFimdirfLinha(numLinha);
                        break;
                    }
                    default: { //VALORES
                        beneficiario.getValoresPorRegistro().put(campos[1], new Valores(numLinha, campos, idRecLinha, idRecCod));
                    }
                }
            }

            dirfRepository.save(dirf);

        } catch (Exception e) {
            e.printStackTrace();
            errors.put(fileName, e);
        }
    }

    private void validaArquivoDirf(BufferedReader dirfFile) throws IOException {
        String[] linhaInicial = Util.splitFirstLineAndReset(dirfFile);
        if (!"DIRF".equalsIgnoreCase(linhaInicial[0])) {
            throw new UnsupportedEncodingException("O arquivo não está no leaiute da DIRF");
        }
        if (!leiautesSuportados.contains(linhaInicial[5])) {
            throw new UnsupportedEncodingException("O leiaute " + linhaInicial[5] + " não é suportado");
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
