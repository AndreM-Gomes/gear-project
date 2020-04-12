package com.andre.gearproject.estoque;

import com.andre.gearproject.produto.Produto;

import com.andre.gearproject.unidade.Unidade;
import com.fasterxml.jackson.annotation.*;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Situacao getSitucao() {
        return situcao;
    }

    public void setSitucao(Situacao situcao) {
        this.situcao = situcao;
    }
}
