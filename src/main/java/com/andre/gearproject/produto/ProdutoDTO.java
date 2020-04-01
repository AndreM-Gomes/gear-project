package com.andre.gearproject.produto;

import com.andre.gearproject.estoque.ItemEstoque;
import lombok.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

@Value
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
}
