package com.andre.gearproject.produto;

import com.andre.gearproject.estoque.ItemEstoque;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;
import java.util.StringJoiner;

@Entity
@Table(name = "TB_Produto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="idProduto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProduto")
    private Integer idProduto;

    @NotNull
    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @NotNull
    @Column(name = "preco")
    private BigDecimal preco;

    @NotNull
    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @OneToMany(mappedBy = "produto",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<ItemEstoque> itensEstoque;

    public Produto(ProdutoDTO produtoDTO){
        this.idProduto = produtoDTO.getIdProduto();
        this.descricao = produtoDTO.getDescricao();
        this.itensEstoque = null;
        this.nome = produtoDTO.getNome();
        this.preco = produtoDTO.getPreco();
        this.tipo = produtoDTO.getTipo();
    }

}
