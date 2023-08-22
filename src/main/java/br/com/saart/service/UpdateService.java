package br.com.saart.service;

import br.com.saart.feign.InfoDto;
import br.com.saart.feign.OneDriveCli;
import com.fasterxml.jackson.core.Version;
import feign.Response;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UpdateService {

    @Autowired
    private OneDriveCli oneDriveCli;

    @Value("${spring.application.ui.version}")
    private String versao;

    @Getter
    private InfoDto ultimaBusca;

    public boolean temAtualizacao() {
        if (ultimaBusca == null) {
            log.info("Obtendo informações de atualização do sistema na internet.");
            ultimaBusca = oneDriveCli.downloadInfo();
        }

        String[] nova = ultimaBusca.getVersao().split("\\.");
        String[] corrente = versao.split("\\.");

        Version versaoNova = new Version(Integer.parseInt(nova[0]), Integer.parseInt(nova[1]), Integer.parseInt(nova[2]), null, null, null);
        Version versaoCorrente = new Version(Integer.parseInt(corrente[0]), Integer.parseInt(corrente[1]), Integer.parseInt(corrente[2]), null, null, null);

        return versaoNova.compareTo(versaoCorrente) > 0;
    }

    public Response baixaAtualizacao() {
        return oneDriveCli.downloadFile(ultimaBusca.getResid(), ultimaBusca.getAuthKey());
    }

    public void reset() {
        ultimaBusca = null;
    }

}
