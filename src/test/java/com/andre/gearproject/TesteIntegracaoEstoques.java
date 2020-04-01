package com.andre.gearproject;

import com.andre.gearproject.estoque.ItemEstoque;
import com.andre.gearproject.estoque.ItemEstoqueDTO;
import com.andre.gearproject.estoque.ItemEstoqueRepository;
import com.andre.gearproject.estoque.Situacao;
import com.andre.gearproject.produto.Produto;
import com.andre.gearproject.produto.ProdutoDTO;
import com.andre.gearproject.produto.ProdutoRepository;
import com.andre.gearproject.produto.Tipo;
import com.andre.gearproject.unidade.Unidade;
import com.andre.gearproject.unidade.UnidadeDTO;
import com.andre.gearproject.unidade.UnidadeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Controller Produto")
class TesteIntegracaoEstoques {

    @Autowired private ItemEstoqueRepository itemEstoqueRepository;
    @Autowired private ProdutoRepository produtoRepository;
    @Autowired private UnidadeRepository unidadeRepository;

    static Produto produto;
    static Unidade unidade;
    static ItemEstoque itemEstoque;
    static ObjectMapper om = new ObjectMapper();

    @DisplayName("Salvar e recuperar um item")
    @Test
    @Order(1)
    public void salvarItens() throws Exception{
        produto = new Produto();

        produto.setNome("Fita Isolante");
        produto.setTipo(Tipo.EQUIPAMENTO);
        produto.setPreco(new BigDecimal("42.70"));
        produto.setDescricao("Fita isolante anti-chama 3M");
        produto.setItensEstoque(new HashSet<>());

        unidade = new Unidade();

        unidade.setNome("Pinheiros");
        unidade.setItensEstoque(new HashSet<>());

        itemEstoque = new ItemEstoque();

        itemEstoque.setUnidade(unidade);
        itemEstoque.setProduto(produto);
        itemEstoque.setQuantidade(10);
        itemEstoque.setSitucao(Situacao.PERFEITO_ESTADO);

        this.produtoRepository.save(produto);
        this.unidadeRepository.save(unidade);
        this.itemEstoqueRepository.save(itemEstoque);

        List<Unidade> unidades = this.unidadeRepository.encontrarTodos();
        unidades.forEach(System.out::println);
    }

}
