package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@IdClass(Bpffci.BpffciId.class)
@EqualsAndHashCode(of = {"fci", "codReceita"})
public class Bpffci {

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "dirf_id", referencedColumnName = "dirf_id"),
            @JoinColumn(name = "fci_id", referencedColumnName = "fci_id")
    })
    Fci fci;

    @Id
    @Column(length = 4, nullable = false)
    String codReceita; //IDREC

    @Column(length = 11, nullable = false)
    String cpf;

    @Column(length = 60, nullable = false)
    String nome;

    LocalDate dataLaudo;

    public class BpffciId implements Serializable {
        Fci fci;
        String codReceita;
    }

}
