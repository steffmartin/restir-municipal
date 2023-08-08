package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@IdClass(Bpffci.BpffciId.class)
@EqualsAndHashCode(of = {"linhaBpffci", "fci"})
public class Bpffci {

    @Id
    @Column(name = "linha_bpffci")
    Integer linhaBpffci;

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "dirf_id", referencedColumnName = "dirf_id"),
            @JoinColumn(name = "linha_fci", referencedColumnName = "linha_fci")
    })
    Fci fci;

    @Column(length = 4, nullable = false)
    String codReceita; //IDREC

    @Column(length = 11, nullable = false)
    String cpf;

    @Column(length = 60, nullable = false)
    String nome;

    LocalDate dataLaudo;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "bpffci_rio",
            joinColumns = {@JoinColumn(name = "linha_bpffci"), @JoinColumn(name = "linha_fci"), @JoinColumn(name = "dirf_id")},
            inverseJoinColumns = {@JoinColumn(name = "linha_rio"), @JoinColumn(name = "dirf_id", insertable = false, updatable = false)}
    )
    Set<Rio> rios; //Apenas 1

    public class BpffciId implements Serializable {
        Fci fci;
        Integer linhaBpffci;
    }

}
