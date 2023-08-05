package br.com.smaconsulting.sped.editor.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Inf {
    @Id
    Integer id;

    @Column(length = 11, nullable = false)
    String cpf;

    @Column(length = 500, nullable = false)
    String informacoes;
}
