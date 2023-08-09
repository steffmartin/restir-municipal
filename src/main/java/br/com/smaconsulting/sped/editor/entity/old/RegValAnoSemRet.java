package br.com.smaconsulting.sped.editor.entity.old;

import javax.persistence.Column;
import javax.persistence.Id;
import java.math.BigDecimal;

public class RegValAnoSemRet {
    @Id
    Integer linhaRegistroAno;

    @Column(length = 6)
    String codRegistro;
    //RIL96
    //RIPTS
    //RIJMRE
    //RIRSR


    BigDecimal vlrAno;

    //DADOS IDREC

    @Column(length = 4)
    String idrecCodigo;

    Integer idrecLinha;
}
