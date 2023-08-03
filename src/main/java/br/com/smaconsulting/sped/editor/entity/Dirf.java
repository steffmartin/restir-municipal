package br.com.smaconsulting.sped.editor.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Year;

@Entity
public class Dirf {

    @Id
    String id;

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
}
