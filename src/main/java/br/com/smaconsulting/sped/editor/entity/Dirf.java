package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.Year;
import java.util.Set;

@Entity
@EqualsAndHashCode(of = {"dirfId"})
public class Dirf {

    @ColumnDefault("1")
    private Integer linhaDirf;

    private Integer linhaFimDirf;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dirf_id", columnDefinition = "serial")
    Integer dirfId;

    @Column(nullable = false)
    Year anoRef;

    @Column(nullable = false)
    Year anoCal;

    @Column(nullable = false)
    Boolean retif;

    @Column(length = 12)
    String numRec;

    @Column(length = 7, nullable = false)
    String idLeiaute;
    // 2023 = ARNZRXP

    @OneToOne(mappedBy = "dirf", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    Respo respo;

    @OneToOne(mappedBy = "dirf", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    Declarante declarante;

    @OneToMany(mappedBy = "dirf", cascade = CascadeType.ALL)
    Set<Fci> fcis;

    @OneToMany(mappedBy = "dirf", cascade = CascadeType.ALL)
    Set<Proc> procs;

    @OneToMany(mappedBy = "dirf", cascade = CascadeType.ALL)
    Set<Rra> rras;

    @OneToMany(mappedBy = "dirf", cascade = CascadeType.ALL)
    Set<Scp> scps;

    @OneToMany(mappedBy = "dirf", cascade = CascadeType.ALL)
    Set<Opse> opses; //PSE

    @OneToMany(mappedBy = "dirf", cascade = CascadeType.ALL)
    Set<Brpde> brpdes; //RPDE

    @OneToMany(mappedBy = "dirf", cascade = CascadeType.ALL)
    Set<Inf> infs;

}
