package br.com.smaconsulting.sped.editor.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class RegValAnoSemRet {
    @Id
    Integer id;

    @Column(length = 6, nullable = false)
    String registro;
    //RIL96
    //RIPTS
    //RIJMRE
    //RIRSR

    @Column(nullable = false)
    BigDecimal vlrAno;
}
