package br.edu.ufabc.aedII.dicionario.controller;

import br.edu.ufabc.aedII.dicionario.model.SearchStructures;
import br.edu.ufabc.aedII.dicionario.repository.Idiomas;
import br.edu.ufabc.aedII.dicionario.service.CorretorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/corretor")
public class CorretorController {

    @Autowired
    CorretorService corretorService;

    @GetMapping("/ingles/{palavra}")
    public CorretorResposta corretorEngilh(@PathVariable String palavra) {
        return this.corretorService.executarCorretor(palavra, SearchStructures.TRIE, Idiomas.ENGLISH);
    }

    @GetMapping("/ingles/red_black/{palavra}")
    public CorretorResposta RedBlackCorretorEngilh(@PathVariable String palavra) {
        return this.corretorService.executarCorretor(palavra, SearchStructures.REED_BLACK, Idiomas.ENGLISH);
    }

    @GetMapping("/ingles/avl/{palavra}")
    public CorretorResposta avlCorretorEngilh(@PathVariable String palavra) {
        return this.corretorService.executarCorretor(palavra, SearchStructures.AVL, Idiomas.ENGLISH);
    }

    @GetMapping("/ingles/hashmap/{palavra}")
    public CorretorResposta HashmapCorretorEngilh(@PathVariable String palavra) {
        return this.corretorService.executarCorretor(palavra, SearchStructures.HASHMAP, Idiomas.ENGLISH);
    }

    @GetMapping("/ingles/array_list/{palavra}")
    public CorretorResposta arrayListCorretorEngilh(@PathVariable String palavra) {
        return this.corretorService.executarCorretor(palavra, SearchStructures.ARRAY_BS, Idiomas.ENGLISH);
    }

    @GetMapping("/espanhol/{palavra}")
    public CorretorResposta corretorSpanish(@PathVariable String palavra) {
        return this.corretorService.executarCorretor(palavra, SearchStructures.TRIE, Idiomas.SPANISH);
    }
    @GetMapping("/espanhol/red_black/{palavra}")
    public CorretorResposta redBlackCorretorSpanish(@PathVariable String palavra) {
        return this.corretorService.executarCorretor(palavra, SearchStructures.REED_BLACK, Idiomas.SPANISH);
    }
    @GetMapping("/espanhol/avl/{palavra}")
    public CorretorResposta avlCorretorSpanish(@PathVariable String palavra) {
        return this.corretorService.executarCorretor(palavra, SearchStructures.AVL, Idiomas.SPANISH);
    }
    @GetMapping("/espanhol/hashmap/{palavra}")
    public CorretorResposta hashmapCorretorSpanish(@PathVariable String palavra) {
        return this.corretorService.executarCorretor(palavra, SearchStructures.HASHMAP, Idiomas.SPANISH);
    }
    @GetMapping("/espanhol/array_list/{palavra}")
    public CorretorResposta arrayListorretorSpanish(@PathVariable String palavra) {
        return this.corretorService.executarCorretor(palavra, SearchStructures.ARRAY_BS, Idiomas.SPANISH);
    }
}
