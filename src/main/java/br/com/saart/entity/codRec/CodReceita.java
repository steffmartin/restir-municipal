package br.com.saart.entity.codRec;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "rec_codigo")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class CodReceita {

    @Id
    @Column(length = 4)
    String id;

    @Column(nullable = false)
    String descricao;

}
