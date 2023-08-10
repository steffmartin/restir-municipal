package br.com.smaconsulting.saart.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Data
@Entity
@Table(name = "dirf_responsavel")
@EqualsAndHashCode(of = "id")
public class Responsavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    Integer id;

    //DADOS REGISTRO RESPO

    @ColumnDefault("2")
    Integer respoLinha;

    @Column(length = 11)
    String cpf;

    @Column(length = 60)
    String nome;

    @Column(length = 2)
    String ddd;

    @Column(length = 9)
    String fone;

    @Column(length = 6)
    String ramal;

    @Column(length = 9)
    String fax;

    @Column(length = 50)
    String email;

    @OneToOne(mappedBy = "responsavel")
    Dirf dirf;

}
