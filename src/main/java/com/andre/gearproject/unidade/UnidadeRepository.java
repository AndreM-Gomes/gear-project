package com.andre.gearproject.unidade;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnidadeRepository extends CrudRepository<Unidade,Integer> {

    @Query("select u from Unidade u join fetch u.itensEstoque i join fetch i.produto")
    List<Unidade> encontrarTodos();
}
