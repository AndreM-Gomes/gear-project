package com.andre.gearproject.estoque;

import com.andre.gearproject.produto.Produto;
import com.andre.gearproject.produto.ProdutoDTO;
import com.andre.gearproject.unidade.Unidade;
import com.andre.gearproject.unidade.UnidadeDTO;
import lombok.Value;

import javax.persistence.*;

public class ItemEstoqueDTO {
    private Integer id;

    private ProdutoDTO produto;

    private UnidadeDTO unidade;

    private Integer quantidade;

    private Situacao situcao;

    public ItemEstoqueDTO(Integer id, ProdutoDTO produto, UnidadeDTO unidade, Integer quantidade, Situacao situcao) {
        this.id = id;
        this.produto = produto;
        this.unidade = unidade;
        this.quantidade = quantidade;
        this.situcao = situcao;
    }

    public ItemEstoqueDTO(ItemEstoque itemEstoque){
        this.id = itemEstoque.getId();
        this.produto = new ProdutoDTO(itemEstoque.getProduto());
        this.unidade = new UnidadeDTO(itemEstoque.getUnidade());
        this.quantidade = itemEstoque.getQuantidade();
        this.situcao = itemEstoque.getSitucao();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProdutoDTO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoDTO produto) {
        this.produto = produto;
    }

    public UnidadeDTO getUnidade() {
        return unidade;
    }

    public void setUnidade(UnidadeDTO unidade) {
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
