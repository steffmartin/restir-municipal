package br.com.smaconsulting.sped.editor.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "dirf_pse_opse")
@EqualsAndHashCode(of = "id")
public class PlanoSaude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    Integer id;

    Integer linhaPse;

    Integer linhaOpse;

    @Column(length = 14)
    String cnpj;

    @Column(length = 150)
    String nome;

    @Column(length = 6)
    String ans;

    @ManyToOne
    @JoinColumn(name = "dirf_id", referencedColumnName = "id")
    Dirf dirf;

//    @OneToMany(mappedBy = "opse", cascade = CascadeType.ALL)
//    Set<Tpse> tpses;

}
