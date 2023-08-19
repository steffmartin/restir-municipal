package br.com.saart.task;

import br.com.saart.feign.InfoDto;
import br.com.saart.feign.OneDriveCli;
import br.com.saart.util.Util;
import com.fasterxml.jackson.core.Version;
import feign.FeignException;
import feign.Response;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Slf4j
public class Updater extends GenericTask {

    @Autowired
    private OneDriveCli oneDriveCli;

    @Getter
    @Value("${spring.application.ui.version}")
    private String versao;

    private InfoDto infoDto = new InfoDto();

    public boolean temAtualizacao() {
        try {
            infoDto = oneDriveCli.downloadInfo();
        } catch (Exception e) {
            log.error("Erro ao fazer download das informações de atualização do sistema.");
            log.debug("Exception", e);
            return false;
        }

        String[] nova = infoDto.getVersao().split("\\.");
        String[] corrente = versao.split("\\.");

        Version versaoNova = new Version(Integer.parseInt(nova[0]), Integer.parseInt(nova[1]), Integer.parseInt(nova[2]), null, null, null);
        Version versaoCorrente = new Version(Integer.parseInt(corrente[0]), Integer.parseInt(corrente[1]), Integer.parseInt(corrente[2]), null, null, null);

        return versaoNova.compareTo(versaoCorrente) > 0;
    }

    public String getInfo() {
        return String.join("\n",
                "Versão atual: " + versao,
                "Nova versão: " + infoDto.getVersao(),
                "Tamanho do download: " + Util.byteToMb(infoDto.getTamanho()) + " MB");
    }

    @Override
    protected Map<String, Throwable> call() throws Exception {
        updateMessage("Iniciando...");

        try (Response response = oneDriveCli.downloadFile(infoDto.getResid(), infoDto.getAuthKey())) {
            updateMessage("Preparando o download...");

            File destFile = new File(System.getProperty("java.io.tmpdir"), "SAART.jar");

            try (InputStream inputStream = response.body().asInputStream(); FileOutputStream outputStream = new FileOutputStream(destFile)) {

                long contentLength = response.headers().get("Content-Length").stream()
                        .findFirst().map(Long::parseLong).orElse(infoDto.getTamanho()); // Tamanho do arquivo

                byte[] buffer = new byte[4096]; // Tamanho do bloco de leitura, fazer download por blocos para poder alimentar a barra de progresso
                long totalBytesRead = 0;
                int bytesRead;

                updateMessage("Fazendo download da atualização, aguarde...");
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                    totalBytesRead += bytesRead;
                    updateProgress(((double) totalBytesRead) / contentLength, 1);
                }
            }

            updateProgress(1, 1);
            updateMessage("Atualização concluída! O SAART irá reiniciar.");
            pc.needRestart = true;
        } catch (FeignException e) {
            updateProgress(1, 1);
            updateMessage("Erro ao fazer o download.");
        } catch (IOException e) {
            updateProgress(1, 1);
            updateMessage("Falha ao gravar o arquivo.");
        } catch (Exception e) {
            updateProgress(1, 1);
            updateMessage("Erro ao atualizar o sistema.");
        }

        return errors;
    }
}
