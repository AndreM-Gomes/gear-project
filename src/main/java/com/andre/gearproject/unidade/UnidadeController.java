package com.andre.gearproject.unidade;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/unidade")
public class UnidadeController {

    private final UnidadeService unidadeService;


    public UnidadeController(UnidadeService unidadeService) {
        this.unidadeService = unidadeService;
    }

    @PostMapping
    ResponseEntity<Unidade> salvarUnidade(@Valid @RequestBody Unidade unidade){
        return unidadeService.salvarUnidade(unidade);
    }
    @GetMapping
    ResponseEntity<List<Unidade>> recuperarTodasUnidades(){
        return unidadeService.recuperarTodasUnidades();
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<Unidade> recuperarUnidadeId(@PathVariable Integer id){
        return unidadeService.retornaUnidadeId(id);
    }
    @PutMapping
    ResponseEntity<Unidade> atualizarUnidade(@Valid @RequestBody Unidade unidade){
        return unidadeService.atualizarUnidade(unidade);
    }
    @DeleteMapping(value = "/{id}")
    ResponseEntity<Unidade> apagarUnidade(@PathVariable Integer id){
        return unidadeService.apagarUnidadeId(id);
    }

}

