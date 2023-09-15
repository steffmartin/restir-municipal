package br.com.saart.entity.dirf;

import br.com.saart.util.Util;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Data
@Entity
@Table(name = "dirf_alimentado")
@EqualsAndHashCode(of = {"id", "infpaLinha"})
@NoArgsConstructor
public class InformacoesAlimentado {

    public InformacoesAlimentado(Integer linha, String[] campo) {
        this.infpaLinha = linha;
        this.cpf = campo[2];
        this.dataNascimento = Util.parseIsoDate(campo[3]);
        this.nome = campo[4];
        this.relDependencia = Util.toShort(campo[5]);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "beneficiario_id", referencedColumnName = "id", nullable = false)
    Beneficiario beneficiario;

    Integer infpaLinha;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "dirf_alimentado_valores",
            joinColumns = {@JoinColumn(name = "alimentado_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "valor_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "cod_registro", length = 7)
    Map<String, Valores> valoresPorRegistro = new HashMap<>();
}
