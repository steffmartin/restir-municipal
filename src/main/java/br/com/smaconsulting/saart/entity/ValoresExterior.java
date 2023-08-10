package br.com.smaconsulting.saart.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "dirf_vrpde")
@EqualsAndHashCode(of = "id")
public class ValoresExterior {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    Integer id;

    @ManyToOne
    @JoinColumn(name = "beneficiario_id", referencedColumnName = "id")
    Beneficiario beneficiario;

    Integer linhaVrpde;

    LocalDate dataPgto;

    @Column(length = 4)
    String codRec;

    Short tipoRendimento;
    //De acordo com a tabela de informações sobre os rendimentos constante na IN que dispõe sobre a Dirf

    @ColumnDefault("0")
    BigDecimal vlrRendimento;

    @ColumnDefault("0")
    BigDecimal vlrRetido;

    Short formaTribut;
    //De acordo com a tabela de informações sobre a forma de tributação constante na IN que dispõe sobre a Dirf

}
