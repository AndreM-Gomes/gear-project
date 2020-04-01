package com.andre.gearproject;

import com.andre.gearproject.unidade.Unidade;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = GearProjectApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Controller Unidade")
public class TesteIntegracaoUnidade {

    @Autowired
    private MockMvc mvc;

    static Unidade unidade;
    static final String uri = "/unidade";
    static ObjectMapper om = new ObjectMapper();

    @BeforeAll
    public static void preparar(){
        unidade = new Unidade();
        unidade.setId(1);
        unidade.setNome("Sé");
    }
    @DisplayName("Salvar e recuperar um produto")
    @Test
    @Order(1)
    public void salvarUmProduto() throws Exception {
        mvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(om.writeValueAsString(unidade)))
                .andExpect(content().string(""))
                .andExpect(status().isCreated());
        mvc.perform(get(uri + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(om.writeValueAsString(unidade)))
                .andExpect(status().isOk());

    }
    @DisplayName("Recuperar uma lista de produtos")
    @Test
    @Order(2)
    public void recuperarListaProdutos() throws Exception{
        Unidade unidade1 = new Unidade();
        unidade1.setId(2);
        unidade1.setNome("Santa Cecília");

        mvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(om.writeValueAsString(unidade)))
                .andExpect(content().string(""))
                .andExpect(status().isCreated());

        List<Unidade> listaEsperada = new ArrayList<>(2);
        listaEsperada.add(unidade);
        listaEsperada.add(unidade1);
        mvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(om.writeValueAsString(unidade1)))
                .andExpect(content().string(""))
                .andExpect(status().isCreated());

        mvc.perform(get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(om.writeValueAsString(listaEsperada.toArray())))
                .andExpect(status().isOk());
    }
    @DisplayName("Atualizar produto")
    @Test
    @Order(3)
    public void atualizarProduto() throws Exception{
        Unidade unidadeAtualizada = new Unidade();
        unidadeAtualizada.setId(1);
        unidadeAtualizada.setNome("Marechal Deodoro");
        mvc.perform(put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(unidadeAtualizada)))
                .andExpect(status().isOk());

        mvc.perform(get(uri + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(om.writeValueAsString(unidadeAtualizada)));
    }
    @DisplayName("Deletar um produto")
    @Test
    @Order(4)
    public void deletarProduto() throws Exception{
        mvc.perform(delete(uri + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mvc.perform(get(uri + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
