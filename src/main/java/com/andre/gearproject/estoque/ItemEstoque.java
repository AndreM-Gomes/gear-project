package com.andre.gearproject.estoque;

import com.andre.gearproject.produto.Produto;

import com.andre.gearproject.unidade.Unidade;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "TB_EstoqueUnidade")
@Getter
@Setter
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class ItemEstoque {

    public ItemEstoque(ItemEstoqueDTO itemEstoqueDTO){
        this.id = itemEstoqueDTO.getId();
        this.produto = new Produto(itemEstoqueDTO.getProduto());
        this.unidade = new Unidade(itemEstoqueDTO.getUnidade());
        this.quantidade = itemEstoqueDTO.getQuantidade();
        this.situcao = itemEstoqueDTO.getSitucao();
    }
    public ItemEstoque(){}
    @Id
    @Column(name = "IdItem")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProduto")
    private Produto produto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUnidade")
    private Unidade unidade;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "situacao")
    @Enumerated(EnumType.STRING)
    private Situacao situcao;

    @Override
    public String toString() {
        return new StringJoiner(", ", ItemEstoque.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("produto=" + produto)
                .add("quantidade=" + quantidade)
                .add("situcao=" + situcao)
                .toString();
    }
}
