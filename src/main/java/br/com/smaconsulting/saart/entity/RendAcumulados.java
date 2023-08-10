package br.com.smaconsulting.saart.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Data
@Entity
@Table(name = "dirf_rra")
@EqualsAndHashCode(of = "id")
public class RendAcumulados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    Integer id;

    Short tipoRra;
    //1 – Pago pelo declarante
    //2 – Pago pela justiça

    @Column(length = 20)
    String numeroRequerimento;

    Short tipoAdv;
    //1 – Pessoa física
    //2 – Pessoa jurídica

    @Column(length = 14)
    String cpfCnpjAdv;

    @Column(length = 150)
    String nomeAdv;

    @ColumnDefault("0")
    BigDecimal vlrAdv;

    @ManyToOne
    @JoinColumn(name = "dirf_id", referencedColumnName = "id")
    Dirf dirf;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "dirf_rra_beneficiario",
            joinColumns = {@JoinColumn(name = "rra_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "beneficiario_id", referencedColumnName = "id")})
    Set<Beneficiario> beneficiarios;
    //BPFRRA

}
