package com.andre.gearproject.unidade;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UnidadeService {

    private final UnidadeRepository unidadeRepository;

    public UnidadeService(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }

    public ResponseEntity<Unidade> salvarUnidade(Unidade unidade) {
        unidadeRepository.save(unidade);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<List<Unidade>> recuperarTodasUnidades() {
        List<Unidade> unidades = (List<Unidade>) unidadeRepository.findAll();
        return new ResponseEntity<>(unidades,HttpStatus.OK);
    }

    public ResponseEntity<Unidade> retornaUnidadeId(Integer id) {
        Optional<Unidade> optionalUnidade = unidadeRepository.findById(id);
        return optionalUnidade.map(
                unidade -> new ResponseEntity<>(unidade,HttpStatus.OK)
        ).orElseGet(
                ()->new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    public ResponseEntity<Unidade> atualizarUnidade(Unidade unidade) {
        unidadeRepository.save(unidade);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Unidade> apagarUnidadeId(Integer id) {
        Optional<Unidade> optionalUnidade = unidadeRepository.findById(id);
        optionalUnidade.ifPresent(unidadeRepository::delete);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
