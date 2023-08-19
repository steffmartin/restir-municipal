package br.com.saart.feign;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class InfoDto {
    @JsonIgnore
    private boolean ok = false;

    private String versao;
    private String resid;
    private String authKey;
    private Long tamanho;
}
