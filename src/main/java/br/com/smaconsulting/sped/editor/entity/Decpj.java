package br.com.smaconsulting.sped.editor.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "dirf_id")
@DiscriminatorValue(value = "DECPJ")
public class Decpj extends Declarante {

    @Column(nullable = false)
    Short natureza;
    //0 – Pessoa jurídica de direito privado
    //1 – Órgãos, autarquias e fundações da administração pública federal
    //2 – Órgãos, autarquias e fundações da administração pública estadual, municipal ou do Distrito Federal
    //3 – Empresa pública ou sociedade de economia mista federal
    //4 – Empresa pública ou sociedade de economia mista estadual, municipal ou do Distrito Federal
    //8 – Entidade com alteração de natureza jurídica (uso restrito)

    @Column(length = 11, nullable = false)
    String cpfResp;

    @Column(nullable = false)
    Boolean decisaoJudicial;

    @Column(nullable = false)
    Boolean fundoClubeInvest;

    @Column(nullable = false)
    Boolean pgtoIsentas;

    @Column(nullable = false)
    Boolean fundPubDirPriv;

    @Column(nullable = false)
    Boolean sitEspecial;

    LocalDate dataEvento;

    @OneToMany(mappedBy = "decpj", cascade = CascadeType.ALL)
    Set<Vpeim> vpeims;
}
