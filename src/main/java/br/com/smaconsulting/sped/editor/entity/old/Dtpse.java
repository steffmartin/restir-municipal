package br.com.smaconsulting.sped.editor.entity.old;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Dtpse {
    @Id
    Integer linhaDtpse;

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "imp_id", referencedColumnName = "imp_id"),
            @JoinColumn(name = "linha_opse", referencedColumnName = "linha_opse"),
            @JoinColumn(name = "linha_tpse", referencedColumnName = "linha_tpse")
    })
    Tpse tpse;

    @Column(length = 11)
    String cpf;

    LocalDate dataNascimento;

    @Column(length = 60)
    String nome;

    Short relDependencia;
    //03 – Cônjuge/ Companheiro (a)
    //04 – Filho (a)
    //06 – Enteado (a)
    //08 – Pai/Mãe
    //10 – Agregado/Outros


    BigDecimal vlrAno;


}
