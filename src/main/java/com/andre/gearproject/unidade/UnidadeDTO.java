package com.andre.gearproject.unidade;

import com.andre.gearproject.estoque.ItemEstoqueDTO;
import lombok.Value;

import java.util.List;

@Value
public class UnidadeDTO {
    private Integer idUnidade;
    private String nome;

    public UnidadeDTO() {
        this.idUnidade = null;
        this.nome = null;
    }

    public UnidadeDTO(Unidade unidade){
        this.idUnidade = unidade.getIdUnidade();
        this.nome = unidade.getNome();
    }
}
