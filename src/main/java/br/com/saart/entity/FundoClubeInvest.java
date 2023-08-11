package br.com.saart.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "dirf_fci")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class FundoClubeInvest {

    public FundoClubeInvest(Integer linha, String[] campo) {
        this.fciLinha = linha;
        this.cnpj = campo[2];
        this.nome = campo[3];
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "dirf_id", referencedColumnName = "id", nullable = false)
    Dirf dirf;

    Integer fciLinha;

    @Column(length = 14)
    String cnpj;

    @Column(length = 150)
    String nome;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "dirf_fci_beneficiario",
            joinColumns = {@JoinColumn(name = "fci_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "beneficiario_id", referencedColumnName = "id")})
    Set<Beneficiario> beneficiarios = new HashSet<>();
    //BPFFCI
    //BPJFCI

}
