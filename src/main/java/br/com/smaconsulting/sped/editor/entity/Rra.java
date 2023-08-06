package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@IdClass(Rra.RraId.class)
@EqualsAndHashCode(of = {"dirf", "rraId"})
public class Rra {
    @Id
    @Column(name = "rra_id")
    Integer rraId; //nº da linha

    @Id
    @ManyToOne
    @JoinColumn(name = "dirf_id", referencedColumnName = "dirf_id")
    Dirf dirf;

    @Column(nullable = false)
    Short tipoRra;
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

    @OneToMany(mappedBy = "rra", cascade = CascadeType.ALL)
    Set<Bpfrra> bpfrras;

    public class RraId implements Serializable {
        Dirf dirf;
        Integer rraId;
    }

}
