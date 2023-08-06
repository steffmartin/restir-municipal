package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@IdClass(Fci.FciId.class)
@EqualsAndHashCode(of = {"dirf", "fciId"})
public class Fci {
    @Id
    @Column(name = "fci_id")
    Integer fciId; //nº da linha

    @Id
    @ManyToOne
    @JoinColumn(name = "dirf_id", referencedColumnName = "dirf_id")
    Dirf dirf;

    @Column(length = 14, nullable = false)
    String cnpj;

    @Column(length = 150, nullable = false)
    String nome;

    @OneToMany(mappedBy = "fci", cascade = CascadeType.ALL)
    Set<Bpffci> bpffcis;

    @OneToMany(mappedBy = "fci", cascade = CascadeType.ALL)
    Set<Bpjfci> bpjfcis;

    public class FciId implements Serializable {
        Dirf dirf;
        Integer fciId;
    }

}
