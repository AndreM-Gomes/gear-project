package com.andre.gearproject.estoque;

import com.andre.gearproject.produto.ProdutoRepository;
import com.andre.gearproject.unidade.Unidade;
import com.andre.gearproject.unidade.UnidadeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemEstoqueService {

    private final ItemEstoqueRepository itemEstoqueRepository;
    private final ProdutoRepository produtoRepository;
    private final UnidadeRepository unidadeRepository;

    public ItemEstoqueService(ItemEstoqueRepository itemEstoqueRepository, ProdutoRepository produtoRepository, UnidadeRepository unidadeRepository) {
        this.itemEstoqueRepository = itemEstoqueRepository;
        this.produtoRepository = produtoRepository;
        this.unidadeRepository = unidadeRepository;
    }

    public ResponseEntity<List<Unidade>> retornaTodosEstoques(){
        return new ResponseEntity<List<Unidade>>(this.unidadeRepository.encontrarTodos(),HttpStatus.OK);
    }

    public ResponseEntity salvarItemEstoque(Integer id, ItemEstoqueDTO itemEstoqueDTO) {
        ItemEstoque itemEstoque = new ItemEstoque(itemEstoqueDTO);
        this.unidadeRepository.save(itemEstoque.getUnidade());
        this.produtoRepository.save(itemEstoque.getProduto());
        this.itemEstoqueRepository.save(itemEstoque);
        return new ResponseEntity(HttpStatus.CREATED);

    }

    public ResponseEntity deleteItemEstoque(Integer id) {
        Optional<ItemEstoque> itemEstoqueOptional = this.itemEstoqueRepository.findById(id);
        itemEstoqueOptional.ifPresent(this.itemEstoqueRepository::delete);
        return new ResponseEntity(HttpStatus.OK);
    }
}
