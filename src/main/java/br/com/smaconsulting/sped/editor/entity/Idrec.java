package br.com.smaconsulting.sped.editor.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Idrec {
    @Id
    Integer id;
    @Column(length = 4, nullable = false)
    String codRec;
}
