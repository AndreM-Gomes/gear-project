package com.andre.gearproject.unidade;

import com.andre.gearproject.estoque.ItemEstoque;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.StringJoiner;

@Entity
@Table(name = "TB_Unidade")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="idUnidade")
@NamedEntityGraph(name = "Unidade.itensEstoque",attributeNodes = @NamedAttributeNode("itensEstoque"))
public class Unidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUnidade")
    private Integer idUnidade;

    @NotNull
    @Column(name = "nome")
    private String nome;

    @OneToMany(mappedBy = "unidade",fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<ItemEstoque> itensEstoque;

    public Unidade(UnidadeDTO unidadeDTO){
        this.idUnidade = unidadeDTO.getIdUnidade();
        this.nome = unidadeDTO.getNome();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Unidade.class.getSimpleName() + "[", "]")
                .add("idUnidade=" + idUnidade)
                .add("nome='" + nome + "'")
                .add("itensEstoque=" + itensEstoque)
                .toString();
    }
}
