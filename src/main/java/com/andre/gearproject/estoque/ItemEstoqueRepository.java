package com.andre.gearproject.estoque;

import com.andre.gearproject.unidade.Unidade;
import com.andre.gearproject.unidade.UnidadeDTO;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ItemEstoqueRepository extends CrudRepository<ItemEstoque,Integer> {

}
