package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@EqualsAndHashCode(of = {"dirfId"})
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "registro", length = 2, discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "NA")
public class Declarante {

    @Column(insertable = false, updatable = false)
    private String registro;

    @Id
    @Column(name = "dirf_id")
    Integer dirfId;

    @OneToOne
    @MapsId("dirf_id")
    @JoinColumn(name = "dirf_id")
    Dirf dirf;

    @Column(length = 14, nullable = false)
    String cpfCnpjDeclarante;

    @Column(length = 150, nullable = false)
    String nomeDeclarante;

    @Column(nullable = false)
    Boolean socioOstensivo;

    @Column(nullable = false)
    Boolean pgtoRendExt;

    @Column(nullable = false)
    Boolean pgtoPlanoSaude;

    @OneToMany(mappedBy = "declarante", cascade = CascadeType.ALL)
    Set<Idrec> idrecs;
}
