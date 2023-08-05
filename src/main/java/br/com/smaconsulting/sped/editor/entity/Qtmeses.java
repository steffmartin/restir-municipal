package br.com.smaconsulting.sped.editor.entity;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Qtmeses {
    @Id
    Integer id;

    @ColumnDefault("0")
    Short jan;
    @ColumnDefault("0")
    Short fev;
    @ColumnDefault("0")
    Short mar;
    @ColumnDefault("0")
    Short abr;
    @ColumnDefault("0")
    Short mai;
    @ColumnDefault("0")
    Short jun;
    @ColumnDefault("0")
    Short jul;
    @ColumnDefault("0")
    Short ago;
    @ColumnDefault("0")
    Short set;
    @ColumnDefault("0")
    Short out;
    @ColumnDefault("0")
    Short nov;
    @ColumnDefault("0")
    Short dez;
}
