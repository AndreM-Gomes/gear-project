package com.andre.gearproject.produto;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "TB_Produto")
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProduto")
    private Integer id;

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
}
