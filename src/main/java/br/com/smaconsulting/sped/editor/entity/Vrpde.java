package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@IdClass(Vrpde.VrpdeId.class)
@EqualsAndHashCode(of = {"linhaVrpde", "brpde"})
public class Vrpde {
    @Id
    Integer linhaVrpde;

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "dirf_id", referencedColumnName = "dirf_id"),
            @JoinColumn(name = "linha_brpde", referencedColumnName = "linha_brpde")
    })
    Brpde brpde;

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

    public class VrpdeId implements Serializable {
        Integer linhaVrpde;
        Brpde brpde;
    }

}
