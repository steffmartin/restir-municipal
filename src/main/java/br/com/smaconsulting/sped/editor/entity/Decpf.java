package br.com.smaconsulting.sped.editor.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name = "dirf_id")
@DiscriminatorValue(value = "DECPF")
public class Decpf extends Declarante {

    @Column(nullable = false)
    Boolean titServNotoriais;

    @Column(nullable = false)
    Boolean espolioSaidaDef;

    LocalDate dataEspolioSaidaDef;

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
