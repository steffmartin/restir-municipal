package br.com.saart.feign;

import lombok.Data;

@Data
public class InfoDto {
    private String versao;
    private String resid;
    private String authKey;
    private Long tamanho;
}
