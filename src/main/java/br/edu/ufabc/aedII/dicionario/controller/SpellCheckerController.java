package br.edu.ufabc.aedII.dicionario.controller;

import br.edu.ufabc.aedII.dicionario.model.SearchStructures;
import br.edu.ufabc.aedII.dicionario.repository.Languages;
import br.edu.ufabc.aedII.dicionario.service.SpellCheckerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/corretor")
public class SpellCheckerController {

    @Autowired
    SpellCheckerService spellCheckerService;

    @GetMapping("/ingles/{palavra}")
    public SpellCheckerResponse corretorEngilh(@PathVariable String palavra) {
        return this.spellCheckerService.run(palavra, SearchStructures.TRIE, Languages.ENGLISH);
    }

    @GetMapping("/ingles/red_black/{palavra}")
    public SpellCheckerResponse RedBlackCorretorEngilh(@PathVariable String palavra) {
        return this.spellCheckerService.run(palavra, SearchStructures.REED_BLACK, Languages.ENGLISH);
    }

    @GetMapping("/ingles/avl/{palavra}")
    public SpellCheckerResponse avlCorretorEngilh(@PathVariable String palavra) {
        return this.spellCheckerService.run(palavra, SearchStructures.AVL, Languages.ENGLISH);
    }

    @GetMapping("/ingles/hashmap/{palavra}")
    public SpellCheckerResponse HashmapCorretorEngilh(@PathVariable String palavra) {
        return this.spellCheckerService.run(palavra, SearchStructures.HASHMAP, Languages.ENGLISH);
    }

    @GetMapping("/ingles/array_list/{palavra}")
    public SpellCheckerResponse arrayListCorretorEngilh(@PathVariable String palavra) {
        return this.spellCheckerService.run(palavra, SearchStructures.ARRAY_BS, Languages.ENGLISH);
    }

    @GetMapping("/espanhol/{palavra}")
    public SpellCheckerResponse corretorSpanish(@PathVariable String palavra) {
        return this.spellCheckerService.run(palavra, SearchStructures.TRIE, Languages.SPANISH);
    }
    @GetMapping("/espanhol/red_black/{palavra}")
    public SpellCheckerResponse redBlackCorretorSpanish(@PathVariable String palavra) {
        return this.spellCheckerService.run(palavra, SearchStructures.REED_BLACK, Languages.SPANISH);
    }
    @GetMapping("/espanhol/avl/{palavra}")
    public SpellCheckerResponse avlCorretorSpanish(@PathVariable String palavra) {
        return this.spellCheckerService.run(palavra, SearchStructures.AVL, Languages.SPANISH);
    }
    @GetMapping("/espanhol/hashmap/{palavra}")
    public SpellCheckerResponse hashmapCorretorSpanish(@PathVariable String palavra) {
        return this.spellCheckerService.run(palavra, SearchStructures.HASHMAP, Languages.SPANISH);
    }
    @GetMapping("/espanhol/array_list/{palavra}")
    public SpellCheckerResponse arrayListorretorSpanish(@PathVariable String palavra) {
        return this.spellCheckerService.run(palavra, SearchStructures.ARRAY_BS, Languages.SPANISH);
    }
}
