package br.com.smaconsulting.sped.editor.entity;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class RegValMes {
    @Id
    String id;

    @Column(length = 5, nullable = false)
    String registro;
    //RTRT
    //RTPO
    //RTPP
    //RTFA
    //RTSP
    //RTEP
    //RTDP
    //RTPA
    //RTIRF
    //CJAC
    //CJAA
    //ESRT
    //ESPO
    //ESPP
    //ESFA
    //ESSP
    //ESEP
    //ESDP
    //ESPA
    //ESIR
    //ESDJ
    //RIP65
    //RIDAC
    //RIIRP
    //RIAP
    //RIMOG
    //RIRPC
    //RIBMR
    //RICAP
    //RISCP
    //RIMUN
    //RISEN
    //DAJUD

    @ColumnDefault("0")
    BigDecimal jan;
    @ColumnDefault("0")
    BigDecimal fev;
    @ColumnDefault("0")
    BigDecimal mar;
    @ColumnDefault("0")
    BigDecimal abr;
    @ColumnDefault("0")
    BigDecimal mai;
    @ColumnDefault("0")
    BigDecimal jun;
    @ColumnDefault("0")
    BigDecimal jul;
    @ColumnDefault("0")
    BigDecimal ago;
    @ColumnDefault("0")
    BigDecimal set;
    @ColumnDefault("0")
    BigDecimal out;
    @ColumnDefault("0")
    BigDecimal nov;
    @ColumnDefault("0")
    BigDecimal dez;
    @ColumnDefault("0")
    BigDecimal decTer;
}
