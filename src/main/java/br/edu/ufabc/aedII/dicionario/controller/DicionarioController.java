package br.edu.ufabc.aedII.dicionario.controller;

import br.edu.ufabc.aedII.dicionario.model.SearchStructures;
import br.edu.ufabc.aedII.dicionario.service.DicionarioInglesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(("/dicionario/ingles"))
public class DicionarioController {

    @Autowired
    DicionarioInglesService dicionarioInglesService;
    @GetMapping("/{palavra}")
    public DicionarioResposta trieSearch(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.dicionarioInglesService.buscarNoDicionario(palavra.toLowerCase(), SearchStructures.TRIE);

        return dicionarioResposta;
    }

    @GetMapping("/red_black/{palavra}")
    public DicionarioResposta redBlackTreeSearch(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.dicionarioInglesService.buscarNoDicionario(palavra.toLowerCase(), SearchStructures.REED_BLACK);

        return dicionarioResposta;
    }

    @GetMapping("/avl/{palavra}")
    public DicionarioResposta avlTreeSearch(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.dicionarioInglesService.buscarNoDicionario(palavra.toLowerCase(), SearchStructures.AVL);

        return dicionarioResposta;
    }

    @GetMapping("/hashmap/{palavra}")
    public DicionarioResposta hashmapSearch(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.dicionarioInglesService.buscarNoDicionario(palavra.toLowerCase(), SearchStructures.HASHMAP);

        return dicionarioResposta;
    }

    @GetMapping("/array_list/{palavra}")
    public DicionarioResposta arrayListSearch(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.dicionarioInglesService.buscarNoDicionario(palavra.toLowerCase(), SearchStructures.HASHMAP);

        return dicionarioResposta;
    }

}
