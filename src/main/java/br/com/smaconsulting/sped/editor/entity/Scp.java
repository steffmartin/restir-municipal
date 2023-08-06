package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@IdClass(Scp.ScpId.class)
@EqualsAndHashCode(of = {"dirf", "linhaScp"})
public class Scp {
    @Id
    @Column(name = "linha_scp")
    Integer linhaScp;

    @Id
    @ManyToOne
    @JoinColumn(name = "dirf_id", referencedColumnName = "dirf_id")
    Dirf dirf;

    @Column(length = 14, nullable = false)
    String cnpj;

    @Column(length = 150, nullable = false)
    String nome;

    @OneToMany(mappedBy = "scp", cascade = CascadeType.ALL)
    Set<Bpfscp> bpfscps;

    @OneToMany(mappedBy = "scp", cascade = CascadeType.ALL)
    Set<Bpjscp> bpjscps;

    public class ScpId implements Serializable {
        Dirf dirf;
        Integer linhaScp;
    }

}
