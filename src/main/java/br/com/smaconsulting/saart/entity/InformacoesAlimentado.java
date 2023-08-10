package br.com.smaconsulting.saart.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Map;

@Data
@Entity
@Table(name = "dirf_infpa")
@EqualsAndHashCode(of = "id")
public class InformacoesAlimentado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    Integer id;

    Integer linhaInfpa;

    @Column(length = 11)
    String cpf;

    LocalDate dataNascimento;

    @Column(length = 60)
    String nome;

    Short relDependencia;
    //03 – Cônjuge/ Companheiro (a)
    //04 – Filho (a)
    //06 – Enteado (a)
    //08 – Pai/Mãe
    //10 – Agregado/Outros

    //DADOS IDREC

    @Column(length = 4)
    String idrecCodigo;

    Integer idrecLinha;

    @ManyToOne
    @JoinColumn(name = "beneficiario_id", referencedColumnName = "id")
    Beneficiario beneficiario;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "dirf_infpa_valores",
            joinColumns = {@JoinColumn(name = "infpa_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "valor_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "cod_registro", length = 7)
    Map<String, Valores> valoresPorRegistro;
}
