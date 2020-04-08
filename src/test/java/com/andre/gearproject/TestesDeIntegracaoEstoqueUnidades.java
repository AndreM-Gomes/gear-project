package com.andre.gearproject;

import com.andre.gearproject.estoque.ItemEstoque;
import com.andre.gearproject.estoque.ItemEstoqueDTO;
import com.andre.gearproject.estoque.Situacao;
import com.andre.gearproject.produto.Produto;
import com.andre.gearproject.produto.Tipo;
import com.andre.gearproject.unidade.Unidade;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestesDeIntegracaoEstoqueUnidades {
    @Autowired
    private MockMvc mvc;

    static Produto produto;
    static Unidade unidade;
    static ItemEstoque itemEstoque;
    static ItemEstoqueDTO itemEstoqueDTO;
    static ObjectMapper om = new ObjectMapper();

    @BeforeAll
    public static void preparar(){
        produto = new Produto();
        produto.setIdProduto(1);
        produto.setNome("Fita Isolante");
        produto.setTipo(Tipo.EQUIPAMENTO);
        produto.setPreco(new BigDecimal("42.70"));
        produto.setDescricao("Fita isolante anti-chama 3M");
        produto.setItensEstoque(new HashSet<>());

        unidade = new Unidade();
        unidade.setIdUnidade(1);
        unidade.setNome("Pinheiros");
        unidade.setItensEstoque(new HashSet<>());

        itemEstoque = new ItemEstoque();
        itemEstoque.setId(1);
        itemEstoque.setUnidade(unidade);
        itemEstoque.setProduto(produto);
        itemEstoque.setQuantidade(10);
        itemEstoque.setSitucao(Situacao.PERFEITO_ESTADO);

        itemEstoqueDTO = new ItemEstoqueDTO(itemEstoque);
    }
    @DisplayName("Salvar e recuperar um produto")
    @Test
    @Order(1)
    public void salvarUmProduto() throws Exception {
        mvc.perform(post("/produto")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(om.writeValueAsString(produto)))
                .andExpect(content().string(""))
                .andExpect(status().isCreated());
        mvc.perform(get("/produto/1")
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

        mvc.perform(post("/produto")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(om.writeValueAsString(produto)))
                .andExpect(content().string(""))
                .andExpect(status().isCreated());

        List<Produto> listaEsperada = new ArrayList<Produto>(2);
        listaEsperada.add(produto);
        listaEsperada.add(produto2);
        mvc.perform(post("/produto")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(om.writeValueAsString(produto2)))
                .andExpect(content().string(""))
                .andExpect(status().isCreated());

        mvc.perform(get("/produto")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(om.writeValueAsString(listaEsperada.toArray())))
                .andExpect(status().isOk());
    }
    @DisplayName("Salvar e recuperar um produto")
    @Test
    @Order(1)
    public void salvarUmaUnidade() throws Exception {
        mvc.perform(post("/unidade")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(om.writeValueAsString(unidade)))
                .andExpect(content().string(""))
                .andExpect(status().isCreated());
        mvc.perform(get("/unidade/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(om.writeValueAsString(unidade)))
                .andExpect(status().isOk());

    }

    @Test
    @Order(2)
    public void recuperarListaUnidades() throws Exception{
        Unidade unidade1 = new Unidade();
        unidade1.setIdUnidade(2);
        unidade1.setNome("Santa Cecília");
        unidade1.setItensEstoque(new HashSet<>());

        mvc.perform(post("/unidade")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(om.writeValueAsString(unidade)))
                .andExpect(content().string(""))
                .andExpect(status().isCreated());

        List<Unidade> listaEsperada = new ArrayList<>(2);
        listaEsperada.add(unidade);
        listaEsperada.add(unidade1);
        mvc.perform(post("/unidade")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(om.writeValueAsString(unidade1)))
                .andExpect(content().string(""))
                .andExpect(status().isCreated());

        mvc.perform(get("/unidade")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(om.writeValueAsString(listaEsperada.toArray())))
                .andExpect(status().isOk());
    }
    @DisplayName("Salvar um item e recuperar uma unidade com o item")
    @Test
    @Order(3)
    public void salvarERecuperar() throws Exception{
        mvc.perform(post("/unidade/3/estoque")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(itemEstoque)))
                .andExpect(status().isCreated());

        mvc.perform(get("/unidade/estoque")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @DisplayName("Apagar item de um estoque")
    @Test
    @Order(4)
    public void apagarItemEstoque() throws Exception{
        mvc.perform(delete("/unidade/1/estoque"))
                .andExpect(status().isOk());
    }
    @DisplayName("Atualizar produto")
    @Test
    @Order(5)
    public void atualizarProduto() throws Exception{
        Produto produtoAtualizado = new Produto();
        produtoAtualizado.setIdProduto(1);
        produtoAtualizado.setNome("Chave Philips");
        produtoAtualizado.setTipo(Tipo.EQUIPAMENTO);
        produtoAtualizado.setPreco(new BigDecimal("42.70"));
        produtoAtualizado.setDescricao("Chave de Philips aço inox");
        produtoAtualizado.setItensEstoque(new HashSet<>());
        mvc.perform(put("/produto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(produtoAtualizado)))
                .andExpect(status().isOk());

        mvc.perform(get( "/produto/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(om.writeValueAsString(produtoAtualizado)));
    }
    @DisplayName("Atualizar Unidade")
    @Test
    @Order(5)
    public void atualizarUnidade() throws Exception{
        Unidade unidadeAtualizada = new Unidade();
        unidadeAtualizada.setIdUnidade(1);
        unidadeAtualizada.setNome("Marechal Deodoro");
        unidadeAtualizada.setItensEstoque(new HashSet<>());
        mvc.perform(put("/unidade")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(unidadeAtualizada)))
                .andExpect(status().isOk());

        mvc.perform(get("/unidade/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(om.writeValueAsString(unidadeAtualizada)));
    }
    @DisplayName("Deletar uma Unidade")
    @Test
    @Order(6)
    public void deletarUnidade() throws Exception{
        mvc.perform(delete("/unidade/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mvc.perform(get("/unidade/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    @DisplayName("Deletar um produto")
    @Test
    @Order(6)
    public void deletarProduto() throws Exception{
        mvc.perform(delete( "/produto/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mvc.perform(get("/produto/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
