package br.com.smaconsulting.sped.editor.entity.old;

import javax.persistence.Column;
import javax.persistence.Id;
import java.math.BigDecimal;

public class Rio {

    @Id
    @Column(name = "dirf_id")
    Integer dirfId; //construtor

    @Id
    @Column(name = "linha_rio")
    Integer linhaRio;

    @Column(length = 6)
    String codRegistroPai; //construtor
    //BPFDEC
    //BPFFCI


    BigDecimal vlrAno;

    @Column(length = 60)
    String descricao;


    //DADOS IDREC

    @Column(length = 4)
    String idrecCodigo;

    Integer idrecLinha;
}
