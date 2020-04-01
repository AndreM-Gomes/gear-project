package com.andre.gearproject.estoque;

import com.andre.gearproject.unidade.Unidade;
import com.andre.gearproject.unidade.UnidadeDTO;
import com.andre.gearproject.unidade.UnidadeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/unidade")
public class EstoqueController {

    private final ItemEstoqueService itemEstoqueService;
    private final UnidadeService unidadeService;

    public EstoqueController(ItemEstoqueService itemEstoqueService, UnidadeService unidadeService) {
        this.itemEstoqueService = itemEstoqueService;
        this.unidadeService = unidadeService;
    }

    @GetMapping("/estoque")
    ResponseEntity<List<Unidade>> todosItensTodasUnidades(){
        return itemEstoqueService.retornaTodosEstoques();
    }

    @PostMapping("/{id}/estoque")
    ResponseEntity salvarItemEstoque(@PathVariable Integer id,@RequestBody ItemEstoque itemEstoque){
        System.out.println(itemEstoque);
        return itemEstoqueService.salvarItemEstoque(id,itemEstoque);
    }
}
