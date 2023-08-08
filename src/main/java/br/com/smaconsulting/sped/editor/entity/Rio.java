package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@IdClass(Rio.RioId.class)
@EqualsAndHashCode(of = {"dirfId", "linhaRio"})
public class Rio {

    @Id
    @Column(name = "dirf_id")
    Integer dirfId; //construtor

    @Id
    @Column(name = "linha_rio")
    Integer linhaRio;

    @Column(length = 6, nullable = false)
    String codRegistroPai; //construtor
    //BPFDEC
    //BPFFCI

    @Column(nullable = false)
    BigDecimal vlrAno;

    @Column(length = 60, nullable = false)
    String descricao;

    public class RioId implements Serializable {
        Integer dirfId;
        Integer linhaRio;
    }
}
