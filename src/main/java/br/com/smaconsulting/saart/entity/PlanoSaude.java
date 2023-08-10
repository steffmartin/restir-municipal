package br.com.smaconsulting.saart.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "dirf_opse")
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

    @OneToMany(mappedBy = "planoSaude", cascade = CascadeType.ALL)
    Set<PlanoSaudeTitular> titulares;

}
