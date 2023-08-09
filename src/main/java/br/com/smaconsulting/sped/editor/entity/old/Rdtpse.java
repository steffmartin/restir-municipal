package br.com.smaconsulting.sped.editor.entity.old;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.math.BigDecimal;

public class Rdtpse {
    @Id
    Integer linhaRdtpse;

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "imp_id", referencedColumnName = "imp_id"),
            @JoinColumn(name = "linha_opse", referencedColumnName = "linha_opse"),
            @JoinColumn(name = "linha_tpse", referencedColumnName = "linha_tpse"),
            @JoinColumn(name = "linha_rtpse", referencedColumnName = "linha_rtpse")
    })
    Rtpse rtpse;

    @Column(length = 14)
    String cpfCnp;

    @Column(length = 150)
    String nome;

    @ColumnDefault("0")
    BigDecimal vlrAnoCal;

    @ColumnDefault("0")
    BigDecimal vlrAnosAnt;


}
