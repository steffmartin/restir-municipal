package br.com.smaconsulting.sped.editor.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Decpf extends Declarante {

    @Column(length = 11, nullable = false)
    String cpf;

    @Column(length = 60, nullable = false)
    String nome;

    @Column(nullable = false)
    Boolean pgtoRendExt;

    @Column(nullable = false)
    Boolean titServNotoriais;

    @Column(nullable = false)
    Boolean pgtoPlanoSaude;

    @Column(nullable = false)
    Boolean socioOstensivo;

    @Column(nullable = false)
    Boolean espolioSaidaDef;

    LocalDate dataEvento;

    Short tipoEvento;
    //1 – Encerramento de espólio
    //2 – Saída definitiva do Brasil

    @Column(nullable = false)
    Boolean falecido;

    LocalDate dataObito;

    Short sitEspolio;
    //0 – Sem espólio
    //1 – Espólio não encerrado

    @Column(length = 11)
    String cpfInvent;

    @Column(length = 60)
    String nomeInvent;
}
