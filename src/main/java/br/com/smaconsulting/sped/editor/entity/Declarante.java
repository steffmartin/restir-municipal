package br.com.smaconsulting.sped.editor.entity;

import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Set;

@Entity
@EqualsAndHashCode(of = {"dirfId"})
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "cod_registro", length = 5, discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "N/A")
public class Declarante {

    @ColumnDefault("3")
    private Integer linhaDec;

    @Column(insertable = false, updatable = false, name = "cod_registro")
    private String codRegistro;

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
    Set<Bpfdec> bpfdecs;

    @OneToMany(mappedBy = "declarante", cascade = CascadeType.ALL)
    Set<Bpjdec> bpjdecs;
}
