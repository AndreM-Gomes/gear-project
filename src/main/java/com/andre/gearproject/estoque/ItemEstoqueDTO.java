package com.andre.gearproject.estoque;

import com.andre.gearproject.produto.Produto;
import com.andre.gearproject.produto.ProdutoDTO;
import com.andre.gearproject.unidade.Unidade;
import com.andre.gearproject.unidade.UnidadeDTO;
import lombok.Value;

import javax.persistence.*;

@Value
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

}
