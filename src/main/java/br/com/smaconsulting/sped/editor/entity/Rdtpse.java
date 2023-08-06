package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@IdClass(Rdtpse.RdtpseId.class)
@EqualsAndHashCode(of = {"linhaRdtpse", "rtpse"})
public class Rdtpse {
    @Id
    Integer linhaRdtpse;

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "dirf_id", referencedColumnName = "dirf_id"),
            @JoinColumn(name = "linha_opse", referencedColumnName = "linha_opse"),
            @JoinColumn(name = "linha_tpse", referencedColumnName = "linha_tpse"),
            @JoinColumn(name = "linha_rtpse", referencedColumnName = "linha_rtpse")
    })
    Rtpse rtpse;

    @Column(length = 14, nullable = false)
    String cpfCnp;

    @Column(length = 150, nullable = false)
    String nome;

    @ColumnDefault("0")
    BigDecimal vlrAnoCal;

    @ColumnDefault("0")
    BigDecimal vlrAnosAnt;

    public class RdtpseId implements Serializable {
        Rtpse rtpse;
        Integer linhaRdtpse;
    }
}
