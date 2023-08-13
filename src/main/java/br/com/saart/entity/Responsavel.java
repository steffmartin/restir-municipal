package br.com.saart.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Data
@Entity
@Table(name = "dirf_responsavel")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Responsavel {

    public Responsavel(Integer linha, String[] campo) {
        this.respoLinha = linha;
        this.cpf = campo[2];
        this.nome = campo[3];
        this.ddd = campo[4];
        this.fone = campo[5];
        this.ramal = campo[6];
        this.fax = campo[7];
        this.email = campo[8];
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne(mappedBy = "responsavel")
    Dirf dirf;

    //DADOS REGISTRO RESPO

    @ColumnDefault("2")
    Integer respoLinha;

    @Column(length = 11)
    String cpf;

    @Column(length = 60)
    String nome;

    @Column(length = 2)
    String ddd;

    @Column(length = 9)
    String fone;

    @Column(length = 6)
    String ramal;

    @Column(length = 9)
    String fax;

    @Column(length = 50)
    String email;

}
