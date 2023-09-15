package br.com.saart.entity.selic;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "selic")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Selic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true)
    LocalDateTime mes;

    @Column(nullable = false)
    BigDecimal valor;

}
