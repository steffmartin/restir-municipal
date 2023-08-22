package br.com.saart.task.dirf;

import br.com.saart.entity.*;
import br.com.saart.service.DirfService;
import br.com.saart.task.GenericTask;
import br.com.saart.util.Util;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
public class ImportDirfTask extends GenericTask {

    private static final List<String> LEIAUTES_SUPORTADOS = List.of("ARNZRXP");

    @Autowired
    private DirfService dirfService;

    //Inputs
    private final String inputDir;

    @Override
    protected Map<String, Throwable> call() {
        updateMessage("Iniciando...");
        StopWatch watch = new StopWatch();
        watch.start();

        updateMessage("Localizando arquivos de DIRF...");
        List<String> fileNames = loadFileNames();

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
            updateMessage("Tarefa finalizada! Tempo gasto em " + progress + " DIRFs: " + Util.toHMS(watch.getTotalTimeMillis()));
        }

        return errors;
    }

    @Transactional
    private void importDirf(String fileName) {
        try (BufferedReader dirfFile = Util.getReader(inputDir + fileName)) {

            validaArquivoDirf(dirfFile);

            Dirf dirf = new Dirf(fileName);
            int numLinha = 0;

            //Últimos
            Integer linhaUltimoIDREC = null;
            String codigoUltimoIDREC = null;
            Beneficiario ultimoBeneficiario = null;
            FundoClubeInvest ultimoFCI = null;
            Processo ultimoPROC = null;
            RendAcumulados ultimoRRA = null;
            InformacoesPrevCompl ultimoInfPrevCompl = null;
            InformacoesAlimentado ultimoInfAlimentado = null;
            SocContaParticipacao ultimoSCP = null;
            Integer linhaUltimoPSE = null;
            PlanoSaude ultimoPlanoSaude = null;
            PlanoSaudeTitular ultimoTitular = null;
            PlanoSaudeDependente ultimoDependente = null;
            Integer linhaUltimoRPDE = null;

            for (String[] campos : dirfFile.lines().map("|"::concat).map(Util::split).toList()) {

                if (dirf.getFimdirfLinha() != null) {
                    break;
                }

                numLinha++;

                switch (campos[1].toUpperCase()) {
                    case "DIRF" -> dirf.initDirf(numLinha, campos);
                    case "FIMDIRF" -> dirf.setFimdirfLinha(numLinha);
                    case "RESPO" -> dirf.setResponsavel(new Responsavel(numLinha, campos));
                    case "DECPF", "DECPJ" -> dirf.setDeclarante(new Declarante(numLinha, campos));
                    case "IDREC" -> {
                        linhaUltimoIDREC = numLinha;
                        codigoUltimoIDREC = campos[2];
                    }
                    case "BPFDEC", "BPJDEC", "VPEIM" -> {
                        ultimoBeneficiario = new Beneficiario(numLinha, campos);
                        dirf.getDeclarante().getBeneficiarios().add(ultimoBeneficiario);
                        ultimoInfAlimentado = null;
                        ultimoInfPrevCompl = null;
                    }
                    case "FCI" -> {
                        ultimoFCI = new FundoClubeInvest(numLinha, campos);
                        dirf.getFcis().add(ultimoFCI);
                    }
                    case "BPFFCI", "BPJFCI" -> {
                        ultimoBeneficiario = new Beneficiario(numLinha, campos);
                        ultimoFCI.getBeneficiarios().add(ultimoBeneficiario);
                    }
                    case "PROC" -> {
                        ultimoPROC = new Processo(numLinha, campos);
                        dirf.getProcs().add(ultimoPROC);
                    }
                    case "BPFPROC", "BPJPROC" -> {
                        ultimoBeneficiario = new Beneficiario(numLinha, campos);
                        ultimoPROC.getBeneficiarios().add(ultimoBeneficiario);
                        ultimoInfAlimentado = null;
                        ultimoInfPrevCompl = null;
                    }
                    case "RRA" -> {
                        ultimoRRA = new RendAcumulados(numLinha, campos);
                        dirf.getRras().add(ultimoRRA);
                    }
                    case "BPFRRA" -> {
                        ultimoBeneficiario = new Beneficiario(numLinha, campos);
                        ultimoRRA.getBeneficiarios().add(ultimoBeneficiario);
                    }
                    case "INFPC" -> {
                        ultimoInfPrevCompl = new InformacoesPrevCompl(numLinha, campos);
                        ultimoBeneficiario.getInfPrevCompls().add(ultimoInfPrevCompl);
                    }
                    case "RTPP", "RTFA", "RTSP", "RTEP", "ESPP", "ESFA", "ESSP", "ESEP" -> {
                        if (BooleanUtils.isTrue(ultimoBeneficiario.getPrevCompl()) && ultimoInfPrevCompl != null) {
                            ultimoInfPrevCompl.getValoresPorRegistro().put(campos[1], new Valores(numLinha, campos, linhaUltimoIDREC, codigoUltimoIDREC));
                        } else {
                            ultimoBeneficiario.getValoresPorRegistro().put(campos[1], new Valores(numLinha, campos, linhaUltimoIDREC, codigoUltimoIDREC));
                        }
                    }
                    case "INFPA" -> {
                        ultimoInfAlimentado = new InformacoesAlimentado(numLinha, campos);
                        ultimoBeneficiario.getInfAlimentados().add(ultimoInfAlimentado);
                    }
                    case "RTPA", "ESPA" -> {
                        if (BooleanUtils.isTrue(ultimoBeneficiario.getAlimentado()) && ultimoInfAlimentado != null) {
                            ultimoInfAlimentado.getValoresPorRegistro().put(campos[1], new Valores(numLinha, campos, linhaUltimoIDREC, codigoUltimoIDREC));
                        } else {
                            ultimoBeneficiario.getValoresPorRegistro().put(campos[1], new Valores(numLinha, campos, linhaUltimoIDREC, codigoUltimoIDREC));
                        }
                    }
                    case "SCP" -> {
                        ultimoSCP = new SocContaParticipacao(numLinha, campos);
                        dirf.getScps().add(ultimoSCP);
                        linhaUltimoIDREC = null;
                        codigoUltimoIDREC = null;
                    }
                    case "BPFSCP", "BPJSCP" -> {
                        ultimoBeneficiario = new Beneficiario(numLinha, campos);
                        ultimoSCP.getBeneficiarios().add(ultimoBeneficiario);
                    }
                    case "PSE" -> linhaUltimoPSE = numLinha;
                    case "OPSE" -> {
                        ultimoPlanoSaude = new PlanoSaude(linhaUltimoPSE, numLinha, campos);
                        dirf.getPses().add(ultimoPlanoSaude);
                        ultimoTitular = null;
                        ultimoDependente = null;
                    }
                    case "TPSE" -> {
                        ultimoTitular = new PlanoSaudeTitular(numLinha, campos);
                        ultimoPlanoSaude.getTitulares().add(ultimoTitular);
                        ultimoDependente = null;
                    }
                    case "RTPSE" -> ultimoTitular.getReembolsos().add(new PlanoSaudeInfReembolso(numLinha, campos));
                    case "DTPSE" -> {
                        ultimoDependente = new PlanoSaudeDependente(numLinha, campos);
                        ultimoTitular.getDependentes().add(ultimoDependente);
                    }
                    case "RDTPSE" -> ultimoDependente.getReembolsos().add(new PlanoSaudeInfReembolso(numLinha, campos));
                    case "RPDE" -> linhaUltimoRPDE = numLinha;
                    case "BRPDE" -> {
                        ultimoBeneficiario = new Beneficiario(numLinha, campos, linhaUltimoRPDE);
                        dirf.getDeclarante().getBeneficiarios().add(ultimoBeneficiario);
                    }
                    case "VRPDE" -> ultimoBeneficiario.getValoresExterior().add(new ValoresExterior(numLinha, campos));
                    case "INF" -> dirf.getInfs().add(new Informacoes(numLinha, campos));
                    default ->
                            ultimoBeneficiario.getValoresPorRegistro().put(campos[1], new Valores(numLinha, campos, linhaUltimoIDREC, codigoUltimoIDREC));
                }
            }

            dirfService.save(dirf);

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

        if (!LEIAUTES_SUPORTADOS.contains(linhaInicial[5])) {
            throw new UnsupportedEncodingException("O leiaute " + linhaInicial[5] + " não é suportado");
        }
    }

    private List<String> loadFileNames() {
        File inputPath = new File(inputDir);

        if (inputPath.isDirectory()) {
            return FileUtils.listFiles(inputPath, new String[]{"txt", "TXT", "dec", "DEC"}, true)
                    .stream().map(file -> StringUtils.removeStart(file.getAbsolutePath(), inputDir))
                    .sorted().collect(toList());
        } else {
            return Collections.emptyList();
        }
    }
}
