package br.com.smaconsulting.sped.editor.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Bpfdec {
    @Id
    String id;

    @Column(length = 11, nullable = false)
    String cpf;

    @Column(length = 60, nullable = false)
    String nome;

    LocalDate dataLaudo;

    @Column(nullable = false)
    Boolean alimentado;

    @Column(nullable = false)
    Boolean prevCompl;


}
