package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@IdClass(Tpse.TpseId.class)
@EqualsAndHashCode(of = {"opse", "linhaTpse"})
public class Tpse {
    @Id
    @Column(name = "linha_tpse")
    Integer linhaTpse;

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "dirf_id", referencedColumnName = "dirf_id"),
            @JoinColumn(name = "linha_opse", referencedColumnName = "linha_opse")
    })
    Opse opse;

    @Column(length = 11, nullable = false)
    String cpf;

    @Column(length = 60, nullable = false)
    String nome;

    @Column(nullable = false)
    BigDecimal vlrAno;

    @OneToMany(mappedBy = "tpse", cascade = CascadeType.ALL)
    Set<Rtpse> rtpses;

    @OneToMany(mappedBy = "tpse", cascade = CascadeType.ALL)
    Set<Dtpse> dtpses;

    public class TpseId implements Serializable {
        Opse opse;
        Integer linhaTpse;
    }

}
