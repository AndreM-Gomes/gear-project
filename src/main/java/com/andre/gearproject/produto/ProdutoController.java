package com.andre.gearproject.produto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService){
        this.produtoService = produtoService;
    }

    @PostMapping
    private ResponseEntity<Produto> salvarProduto(@Valid @RequestBody Produto produto){
        return produtoService.salvarProduto(produto);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> pesquisarProdutos(){
        return produtoService.retornaTodosProdutos();
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> pesquisarProdutoPorId(@PathVariable Integer id){
        return produtoService.retornaProdutoId(id);
    }
    @PutMapping
    public ResponseEntity<Produto> atualizarProduto(@Valid @RequestBody Produto produto){
        return produtoService.atualizarProdutoPorId(produto);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Produto> apagarProduto(@PathVariable Integer id){
        return produtoService.apagarProdutoPorId(id);
    }

}