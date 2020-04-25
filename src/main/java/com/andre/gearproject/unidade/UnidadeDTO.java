package com.andre.gearproject.unidade;

import com.andre.gearproject.estoque.ItemEstoqueDTO;
import lombok.Value;

import java.util.List;

public class UnidadeDTO {
    private Integer idUnidade;
    private String nome;

    public UnidadeDTO() { }

    public UnidadeDTO(Unidade unidade){
        this.idUnidade = unidade.getIdUnidade();
        this.nome = unidade.getNome();
    }

    public Integer getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(Integer idUnidade) {
        this.idUnidade = idUnidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
