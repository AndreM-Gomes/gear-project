package com.andre.gearproject;

import com.andre.gearproject.produto.Produto;
import com.andre.gearproject.produto.Tipo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = GearProjectApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Controller Produto")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
        produto.setIdProduto(1);
        produto.setItensEstoque(new HashSet<>());
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
    @DisplayName("Recuperar uma lista de produtos")
    @Test
    @Order(2)
    public void recuperarListaProdutos() throws Exception{
        Produto produto2 = new Produto();
        produto2.setIdProduto(2);
        produto2.setDescricao("Fita isolante anti-chama 3M");
        produto2.setPreco(new BigDecimal("32.60"));
        produto2.setTipo(Tipo.INSUMO);
        produto2.setNome("Fita isolante");
        produto2.setItensEstoque(new HashSet<>());

        mvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(om.writeValueAsString(produto)))
                .andExpect(content().string(""))
                .andExpect(status().isCreated());

        List<Produto> listaEsperada = new ArrayList<Produto>(2);
        listaEsperada.add(produto);
        listaEsperada.add(produto2);
        mvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(om.writeValueAsString(produto2)))
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
        Produto produtoAtualizado = new Produto();
        produtoAtualizado.setIdProduto(1);
        produtoAtualizado.setNome("Chave Philips");
        produtoAtualizado.setTipo(Tipo.EQUIPAMENTO);
        produtoAtualizado.setPreco(new BigDecimal("42.70"));
        produtoAtualizado.setDescricao("Chave de Philips aço inox");
        produtoAtualizado.setItensEstoque(new HashSet<>());
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
