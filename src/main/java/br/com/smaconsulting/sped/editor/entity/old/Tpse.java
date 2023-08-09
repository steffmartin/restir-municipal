package br.com.smaconsulting.sped.editor.entity.old;

import br.com.smaconsulting.sped.editor.entity.PlanoSaude;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

public class Tpse {
    @Id
    @Column(name = "linha_tpse")
    Integer linhaTpse;

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "imp_id", referencedColumnName = "imp_id"),
            @JoinColumn(name = "linha_opse", referencedColumnName = "linha_opse")
    })
    PlanoSaude opse;

    @Column(length = 11)
    String cpf;

    @Column(length = 60)
    String nome;


    BigDecimal vlrAno;

    @OneToMany(mappedBy = "tpse", cascade = CascadeType.ALL)
    Set<Rtpse> rtpses;

    @OneToMany(mappedBy = "tpse", cascade = CascadeType.ALL)
    Set<Dtpse> dtpses;


}
