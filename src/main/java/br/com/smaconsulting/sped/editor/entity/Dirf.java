package br.com.smaconsulting.sped.editor.entity;

import javax.persistence.*;
import java.time.Year;

@Entity
public class Dirf {

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
}
