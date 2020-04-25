package com.andre.gearproject.produto;

import com.andre.gearproject.estoque.ItemEstoque;
import lombok.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

public class ProdutoDTO {
    private Integer idProduto;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Tipo tipo;

    public ProdutoDTO(Integer idProduto, String nome, String descricao, BigDecimal preco, Tipo tipo) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.tipo = tipo;
    }

    public ProdutoDTO(Produto produto) {
        this.idProduto =  produto.getIdProduto();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.preco = produto.getPreco();
        this.tipo = produto.getTipo();
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
}
