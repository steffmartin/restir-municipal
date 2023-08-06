package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@IdClass(Proc.ProcId.class)
@EqualsAndHashCode(of = {"dirfId", "procId"})
public class Proc {
    @Id
    Integer procId; //nº da linha

    @Id
    @Column(name = "dirf_id")
    Integer dirfId;

    @ManyToOne
    @MapsId("dirf_id")
    @JoinColumn(name = "dirf_id")
    Dirf dirf;

    @Column(nullable = false)
    Short idJustica;
    //1 – Justiça federal
    //2 – Justiça do trabalho
    //3 – Justiça estadual/Distrito Federal

    @Column(length = 20, nullable = false)
    String numProc;

    Short tipoAdv;
    //1 – Pessoa física
    //2 – Pessoa jurídica

    @Column(length = 14)
    String cpfCnpjAdv;

    @Column(length = 150)
    String nomeAdv;

    @ColumnDefault("0")
    BigDecimal vlrAdv;

    public class ProcId implements Serializable {
        Integer dirfId;
        Integer procId;
    }

}
