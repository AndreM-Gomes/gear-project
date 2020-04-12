package com.andre.gearproject.unidade;

import com.andre.gearproject.estoque.ItemEstoque;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.StringJoiner;

@Entity
@Table(name = "TB_Unidade")

@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="idUnidade")
@NamedEntityGraph(name = "Unidade.itensEstoque",attributeNodes = @NamedAttributeNode("itensEstoque"))
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Unidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUnidade")
    private Integer idUnidade;

    @NotNull
    @Column(name = "nome")
    private String nome;


    @OneToMany(mappedBy = "unidade",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<ItemEstoque> itensEstoque;

    public Unidade(UnidadeDTO unidadeDTO){
        this.idUnidade = unidadeDTO.getIdUnidade();
        this.nome = unidadeDTO.getNome();
    }

    public Unidade(Integer idUnidade, @NotNull String nome, Set<ItemEstoque> itensEstoque) {
        this.idUnidade = idUnidade;
        this.nome = nome;
        this.itensEstoque = itensEstoque;
    }

    public Unidade(){}

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

    public Set<ItemEstoque> getItensEstoque() {
        return itensEstoque;
    }

    public void setItensEstoque(Set<ItemEstoque> itensEstoque) {
        this.itensEstoque = itensEstoque;
    }


}
