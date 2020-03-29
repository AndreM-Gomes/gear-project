package com.andre.gearproject;

import com.andre.gearproject.produto.Produto;
import com.andre.gearproject.produto.Tipo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GearProjectApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Controller Produto")
class TesteIntegracaoProduto {

    @Autowired
    private MockMvc mvc;

    static Produto produto;
    static final String uri = "/produto";
    static ObjectMapper om = new ObjectMapper();

    @BeforeAll
    public static void preparar(){
        produto = new Produto();
        produto.setNome("Chave de fenda");
        produto.setTipo(Tipo.EQUIPAMENTO);
        produto.setPreco(new BigDecimal("42.70"));
        produto.setDescricao("Chave de fenda de aço inox");
        produto.setId(1);
    }
    @DisplayName("Salvar e recuperar um produto")
    @Test
    @Order(1)
    public void salvarUmProduto() throws Exception {
        mvc.perform(post(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding("utf-8")
                    .content(om.writeValueAsString(produto)))
                .andExpect(content().string(""))
                .andExpect(status().isCreated());
        mvc.perform(get(uri + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(om.writeValueAsString(produto)))
                .andExpect(status().isOk());

    }
    @DisplayName("Atualizar produto")
    @Test
    @Order(2)
    public void atualizarProduto() throws Exception{
        Produto produtoAtualizado = new Produto();
        produtoAtualizado.setId(1);
        produtoAtualizado.setNome("Chave Philips");
        produtoAtualizado.setTipo(Tipo.EQUIPAMENTO);
        produtoAtualizado.setPreco(new BigDecimal("42.70"));
        produtoAtualizado.setDescricao("Chave de Philips aço inox");
        mvc.perform(put(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsString(produtoAtualizado)))
            .andExpect(status().isOk());

        mvc.perform(get(uri + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(om.writeValueAsString(produtoAtualizado)));
    }
    @DisplayName("Deletar um produto")
    @Test
    @Order(3)
    public void deletarProduto() throws Exception{
        mvc.perform(delete(uri + "/1")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        mvc.perform(get(uri + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}