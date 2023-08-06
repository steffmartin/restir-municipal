package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@EqualsAndHashCode(of = {"dirf"})
public class Respo {

    @ColumnDefault("2")
    private Integer linhaRespo;

    @Id
    @Column(name = "dirf_id")
    Integer dirfId;

    @OneToOne
    @MapsId("dirf_id")
    @JoinColumn(name = "dirf_id")
    Dirf dirf;

    @Column(length = 11, nullable = false)
    String cpf;

    @Column(length = 60, nullable = false)
    String nome;

    @Column(length = 2, nullable = false)
    String ddd;

    @Column(length = 9, nullable = false)
    String fone;

    @Column(length = 6)
    String ramal;

    @Column(length = 9)
    String fax;

    @Column(length = 50)
    String email;

}
