package com.andre.gearproject.produto;

import com.andre.gearproject.estoque.ItemEstoque;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="idProduto")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

    public Produto() {

    }

    public Produto(Integer idProduto, @NotNull String nome, String descricao, @NotNull BigDecimal preco, @NotNull Tipo tipo, Set<ItemEstoque> itensEstoque) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.tipo = tipo;
        this.itensEstoque = itensEstoque;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Set<ItemEstoque> getItensEstoque() {
        return itensEstoque;
    }

    public void setItensEstoque(Set<ItemEstoque> itensEstoque) {
        this.itensEstoque = itensEstoque;
    }
}
