package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(Bpfscp.BpfscpId.class)
@EqualsAndHashCode(of = {"linhaBpfscp", "scp"})
public class Bpfscp {
    @Id
    Integer linhaBpfscp;

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "dirf_id", referencedColumnName = "dirf_id"),
            @JoinColumn(name = "linha_scp", referencedColumnName = "linha_scp")
    })
    Scp scp;

    @Column(length = 11, nullable = false)
    String cpf;

    @Column(length = 60, nullable = false)
    String nome;

    Float participacao;

    public class BpfscpId implements Serializable {
        Scp scp;
        Integer linhaBpfscp;
    }

}
