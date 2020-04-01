package com.andre.gearproject.estoque;

import com.andre.gearproject.unidade.Unidade;
import com.andre.gearproject.unidade.UnidadeDTO;
import com.andre.gearproject.unidade.UnidadeRepository;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class ItemEstoqueService {

    private final ItemEstoqueRepository itemEstoqueRepository;
    private final UnidadeRepository unidadeRepository;

    public ItemEstoqueService(ItemEstoqueRepository itemEstoqueRepository, UnidadeRepository unidadeRepository) {
        this.itemEstoqueRepository = itemEstoqueRepository;
        this.unidadeRepository = unidadeRepository;
    }

    public ResponseEntity<List<Unidade>> retornaTodosEstoques(){
        return new ResponseEntity<List<Unidade>>( HttpStatus.OK);
    }

    public ResponseEntity salvarItemEstoque(Integer id, ItemEstoque itemEstoque) {

            this.itemEstoqueRepository.save(itemEstoque);
            return new ResponseEntity(new ItemEstoqueDTO(this.itemEstoqueRepository.findById(id).get()),HttpStatus.CREATED);

    }
}
