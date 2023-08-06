package br.com.smaconsulting.sped.editor.entity;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Vrpde {
    @Id
    Integer linhaVrpde;

    @Column(nullable = false)
    LocalDate dataPgto;

    @Column(length = 4, nullable = false)
    String codRec;

    @Column(nullable = false)
    Short tipoRendimento;
    //De acordo com a tabela de informações sobre os rendimentos constante na IN que dispõe sobre a Dirf

    @Column(nullable = false)
    BigDecimal vlrRendimento;

    @ColumnDefault("0")
    BigDecimal vlrRetido;

    @Column(nullable = false)
    Short formaTribut;
    //De acordo com a tabela de informações sobre a forma de tributação constante na IN que dispõe sobre a Dirf


}
