package com.andre.gearproject.unidade;

import com.andre.gearproject.estoque.ItemEstoqueDTO;
import lombok.Value;

import java.util.List;

@Value
public class UnidadeDTO {
    private Integer idUnidade;
    private String nome;
    private List<ItemEstoqueDTO> itensEstoque;


    public UnidadeDTO(Integer idUnidade, String nome) {
        this.idUnidade = idUnidade;
        this.nome = nome;
        this.itensEstoque = null;
    }

    public UnidadeDTO() {
        this.idUnidade = null;
        this.nome = null;
        this.itensEstoque = null;
    }

    public UnidadeDTO(Integer idUnidade, String nome, List<ItemEstoqueDTO> itensEstoque) {
        this.idUnidade = idUnidade;
        this.nome = nome;
        this.itensEstoque = itensEstoque;
    }

    public UnidadeDTO(Unidade unidade){
        this.idUnidade = unidade.getIdUnidade();
        this.nome = unidade.getNome();
        this.itensEstoque = null;
    }
}
