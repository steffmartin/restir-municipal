package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@IdClass(Proc.ProcId.class)
@EqualsAndHashCode(of = {"dirf", "linhaProc"})
public class Proc {
    @Id
    @Column(name = "linha_proc")
    Integer linhaProc;

    @Id
    @ManyToOne
    @JoinColumn(name = "dirf_id", referencedColumnName = "dirf_id")
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

    @OneToMany(mappedBy = "proc", cascade = CascadeType.ALL)
    Set<Bpfproc> bpfprocs;

    @OneToMany(mappedBy = "proc", cascade = CascadeType.ALL)
    Set<Bpjproc> bpjprocs;

    public class ProcId implements Serializable {
        Dirf dirf;
        Integer linhaProc;
    }

}
