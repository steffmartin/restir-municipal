package br.com.saart.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "dirf_inf")
@EqualsAndHashCode(of = "id")
public class Informacoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "dirf_id", referencedColumnName = "id", nullable = false)
    Dirf dirf;

    Integer infLinha;

    @Column(length = 11)
    String cpf;

    @Column(length = 500)
    String informacoes;

}
