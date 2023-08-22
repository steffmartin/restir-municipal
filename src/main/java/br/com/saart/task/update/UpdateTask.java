package br.com.saart.task.update;

import br.com.saart.service.UpdateService;
import br.com.saart.task.GenericTask;
import feign.FeignException;
import feign.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class UpdateTask extends GenericTask {

    @Autowired
    private UpdateService updateService;

    @Override
    protected Map<String, Throwable> call() throws Exception {
        updateMessage("Iniciando...");

        try (Response response = updateService.baixaAtualizacao()) {

            updateMessage("Preparando o download...");
            File destFile = new File(System.getProperty("java.io.tmpdir"), "SAART.jar");

            try (InputStream inputStream = response.body().asInputStream(); FileOutputStream outputStream = new FileOutputStream(destFile)) {

                long contentLength = response.headers().get("Content-Length").stream().findFirst().map(Long::parseLong).orElse(updateService.getUltimaBusca().getTamanho());
                byte[] buffer = new byte[4096];
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
        } catch (FeignException e) {
            updateProgress(1, 1);
            updateMessage("Erro ao fazer o download.");
        } catch (IOException e) {
            updateProgress(1, 1);
            updateMessage("Falha ao gravar o arquivo.");
        } catch (Exception e) {
            updateProgress(1, 1);
            updateMessage("Erro ao atualizar o sistema.");
        } finally {
            updateService.reset();
        }

        return errors;
    }

}
