package br.com.smaconsulting.sped.editor.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Respo {

    @Id
    String id;

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
