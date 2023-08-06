package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(Bpjfci.BpjfciId.class)
@EqualsAndHashCode(of = {"fci", "codReceita"})
public class Bpjfci {
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

    @Column(length = 14, nullable = false)
    String cnpj;

    @Column(length = 150, nullable = false)
    String nome;

    public class BpjfciId implements Serializable {
        Fci fci;
        String codReceita;
    }
}
