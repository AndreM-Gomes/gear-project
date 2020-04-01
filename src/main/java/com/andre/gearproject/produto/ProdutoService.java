package com.andre.gearproject.produto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ResponseEntity<Produto> salvarProduto(Produto produto){
        produtoRepository.save(produto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    public ResponseEntity<List<Produto>> retornaTodosProdutos(){
        List<Produto> produtos = (List<Produto>) produtoRepository.findAll();
        return new ResponseEntity<List<Produto>>(produtos,HttpStatus.OK);
    }

    public ResponseEntity<Produto> retornaProdutoId(int id){
        Optional<Produto> optionalProduto = produtoRepository.findById(id);
        return optionalProduto.map(
                produto -> new ResponseEntity<>(produto, HttpStatus.OK)
        ).orElseGet(
                () -> new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );

    }
    public ResponseEntity<Produto> atualizarProdutoPorId(Produto produto){
        produtoRepository.save(produto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    public ResponseEntity<Produto> apagarProdutoPorId(int id){
        Optional<Produto> optionalProduto = produtoRepository.findById(id);
        optionalProduto.ifPresent(produtoRepository::delete);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
