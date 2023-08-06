package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(Bpjscp.BpjscpId.class)
@EqualsAndHashCode(of = {"linhaBpjscp", "scp"})
public class Bpjscp {
    @Id
    Integer linhaBpjscp;

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "dirf_id", referencedColumnName = "dirf_id"),
            @JoinColumn(name = "linha_scp", referencedColumnName = "linha_scp")
    })
    Scp scp;

    @Column(length = 14, nullable = false)
    String cnpj;

    @Column(length = 150, nullable = false)
    String nome;

    Float participacao;

    public class BpjscpId implements Serializable {
        Scp scp;
        Integer linhaBpjscp;
    }

}
