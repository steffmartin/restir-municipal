package br.com.saart.entity.selic;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

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
    LocalDate periodo;

    @Column(nullable = false)
    BigDecimal valor;

    BigDecimal valorAcumulado;

}
