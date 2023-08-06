package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(Bpjfci.BpjfciId.class)
@EqualsAndHashCode(of = {"fci", "linhaBpjfci"})
public class Bpjfci {
    @Id
    Integer linhaBpjfci;

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "dirf_id", referencedColumnName = "dirf_id"),
            @JoinColumn(name = "linha_fci", referencedColumnName = "linha_fci")
    })
    Fci fci;

    @Column(length = 4, nullable = false)
    String codReceita; //IDREC

    @Column(length = 14, nullable = false)
    String cnpj;

    @Column(length = 150, nullable = false)
    String nome;

    public class BpjfciId implements Serializable {
        Fci fci;
        Integer linhaBpjfci;
    }
}
