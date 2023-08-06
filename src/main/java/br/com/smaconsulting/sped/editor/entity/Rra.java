package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@IdClass(Rra.RraId.class)
@EqualsAndHashCode(of = {"dirfId", "rraId"})
public class Rra {
    @Id
    Integer rraId; //nº da linha

    @Id
    @Column(name = "dirf_id")
    Integer dirfId;

    @ManyToOne
    @MapsId("dirf_id")
    @JoinColumn(name = "dirf_id")
    Dirf dirf;

    @Column(nullable = false)
    Short idRra;
    //1 – Pago pelo declarante
    //2 – Pago pela justiça

    @Column(length = 20)
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

    public class RraId implements Serializable {
        Integer dirfId;
        Integer rraId;
    }

}
