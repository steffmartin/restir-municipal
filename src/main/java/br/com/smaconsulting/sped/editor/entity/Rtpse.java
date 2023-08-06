package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@IdClass(Rtpse.RtpseId.class)
@EqualsAndHashCode(of = {"linhaRtpse", "tpse"})
public class Rtpse {
    @Id
    @Column(name = "linha_rtpse")
    Integer linhaRtpse;

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "dirf_id", referencedColumnName = "dirf_id"),
            @JoinColumn(name = "linha_opse", referencedColumnName = "linha_opse"),
            @JoinColumn(name = "linha_tpse", referencedColumnName = "linha_tpse")
    })
    Tpse tpse;

    @Column(length = 14, nullable = false)
    String cpfCnp;

    @Column(length = 150, nullable = false)
    String nome;

    @ColumnDefault("0")
    BigDecimal vlrAnoCal;

    @ColumnDefault("0")
    BigDecimal vlrAnosAnt;

    @OneToMany(mappedBy = "rtpse", cascade = CascadeType.ALL)
    Set<Rdtpse> rdtpses;

    public class RtpseId implements Serializable {
        Tpse tpse;
        Integer linhaRtpse;
    }
}
