package com.andre.gearproject.unidade;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TB_Unidade")
@Data
public class Unidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUnidade")
    private Integer id;

    @NotNull
    @Column(name = "nome")
    private String nome;
}
