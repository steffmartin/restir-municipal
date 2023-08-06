package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(Qtmeses.QtmesesId.class)
@EqualsAndHashCode(of = {"bpfrra"})
public class Qtmeses {

    Integer linhaQtmeses;

    @Id
    @Column(name = "dirf_id")
    Integer dirfId;

    @Id
    @Column(name = "linha_rra")
    Integer linhaRra;

    @Id
    @Column(name = "linha_bpfrra")
    Integer linhaBpfrra;

    @MapsId
    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "dirf_id", referencedColumnName = "dirf_id"),
            @JoinColumn(name = "linha_rra", referencedColumnName = "linha_rra"),
            @JoinColumn(name = "linha_bpfrra", referencedColumnName = "linha_bpfrra")
    })
    Bpfrra bpfrra;

    @ColumnDefault("0")
    Short jan;
    @ColumnDefault("0")
    Short fev;
    @ColumnDefault("0")
    Short mar;
    @ColumnDefault("0")
    Short abr;
    @ColumnDefault("0")
    Short mai;
    @ColumnDefault("0")
    Short jun;
    @ColumnDefault("0")
    Short jul;
    @ColumnDefault("0")
    Short ago;
    @ColumnDefault("0")
    Short set;
    @ColumnDefault("0")
    Short out;
    @ColumnDefault("0")
    Short nov;
    @ColumnDefault("0")
    Short dez;

    public class QtmesesId implements Serializable {
        Integer dirfId;
        Integer linhaRra;
        Integer linhaBpfrra;
    }

}
