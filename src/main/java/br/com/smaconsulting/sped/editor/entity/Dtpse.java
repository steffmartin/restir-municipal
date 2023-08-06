package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@IdClass(Dtpse.DtpseId.class)
@EqualsAndHashCode(of = {"linhaDtpse", "tpse"})
public class Dtpse {
    @Id
    Integer linhaDtpse;

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "dirf_id", referencedColumnName = "dirf_id"),
            @JoinColumn(name = "linha_opse", referencedColumnName = "linha_opse"),
            @JoinColumn(name = "linha_tpse", referencedColumnName = "linha_tpse")
    })
    Tpse tpse;

    @Column(length = 11)
    String cpf;

    LocalDate dataNascimento;

    @Column(length = 60, nullable = false)
    String nome;

    Short relDependencia;
    //03 – Cônjuge/ Companheiro (a)
    //04 – Filho (a)
    //06 – Enteado (a)
    //08 – Pai/Mãe
    //10 – Agregado/Outros

    @Column(nullable = false)
    BigDecimal vlrAno;

    public class DtpseId implements Serializable {
        Tpse tpse;
        Integer linhaDtpse;
    }

}
