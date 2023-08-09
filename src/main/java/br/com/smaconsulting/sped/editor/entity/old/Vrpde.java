package br.com.smaconsulting.sped.editor.entity.old;

import br.com.smaconsulting.sped.editor.entity.Beneficiario;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Vrpde {
    @Id
    Integer linhaVrpde;

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "imp_id", referencedColumnName = "imp_id"),
            @JoinColumn(name = "linha_brpde", referencedColumnName = "linha_brpde")
    })
    Beneficiario brpde;


    LocalDate dataPgto;

    @Column(length = 4)
    String codRec;


    Short tipoRendimento;
    //De acordo com a tabela de informações sobre os rendimentos constante na IN que dispõe sobre a Dirf


    BigDecimal vlrRendimento;

    @ColumnDefault("0")
    BigDecimal vlrRetido;


    Short formaTribut;
    //De acordo com a tabela de informações sobre a forma de tributação constante na IN que dispõe sobre a Dirf


}
