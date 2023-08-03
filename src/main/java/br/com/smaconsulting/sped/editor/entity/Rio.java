package br.com.smaconsulting.sped.editor.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Rio {
    @Id
    String id;

    @Column(nullable = false)
    BigDecimal vlrAno;

    @Column(length = 60, nullable = false)
    String descricao;
}
