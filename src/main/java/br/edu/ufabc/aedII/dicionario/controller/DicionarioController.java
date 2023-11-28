package br.edu.ufabc.aedII.dicionario.controller;

import br.edu.ufabc.aedII.dicionario.model.SearchStructures;
import br.edu.ufabc.aedII.dicionario.service.DicionarioEspanholService;
import br.edu.ufabc.aedII.dicionario.service.DicionarioInglesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(("/dicionario"))
public class DicionarioController {

    @Autowired
    DicionarioInglesService dicionarioInglesService;
    @Autowired
    DicionarioEspanholService dicionarioEspanholService;

    @GetMapping("/ingles/{palavra}")
    public ResponseEntity<DicionarioResposta> trieSearchEnglish(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.dicionarioInglesService.buscarNoDicionario(palavra.toLowerCase(), SearchStructures.TRIE);

        return ResponseEntity.ok(dicionarioResposta);
    }

    @GetMapping("/ingles/red_black/{palavra}")
    public ResponseEntity<DicionarioResposta> redBlackTreeSearchEnglish(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.dicionarioInglesService.buscarNoDicionario(palavra.toLowerCase(), SearchStructures.REED_BLACK);

        return ResponseEntity.ok(dicionarioResposta);
    }

    @GetMapping("/ingles/avl/{palavra}")
    public ResponseEntity<DicionarioResposta> avlTreeSearchEnglish(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.dicionarioInglesService.buscarNoDicionario(palavra.toLowerCase(), SearchStructures.AVL);

        return ResponseEntity.ok(dicionarioResposta);
    }

    @GetMapping("/ingles/hashmap/{palavra}")
    public ResponseEntity<DicionarioResposta> hashmapSearchEnglish(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.dicionarioInglesService.buscarNoDicionario(palavra.toLowerCase(), SearchStructures.HASHMAP);

        return ResponseEntity.ok(dicionarioResposta);
    }

    @GetMapping("/ingles/array_list/{palavra}")
    public ResponseEntity<DicionarioResposta> arrayListSearchEnglish(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.dicionarioInglesService.buscarNoDicionario(palavra.toLowerCase(), SearchStructures.HASHMAP);

        return ResponseEntity.ok(dicionarioResposta);
    }

    @GetMapping("/espanhol/{palavra}")
    public ResponseEntity<DicionarioResposta> trieSearchSpanish(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.dicionarioEspanholService.buscarNoDicionario(palavra.toLowerCase(), SearchStructures.TRIE);

        return ResponseEntity.ok(dicionarioResposta);
    }

    @GetMapping("/espanhol/red_black/{palavra}")
    public ResponseEntity<DicionarioResposta> redBlackTreeSearchSpanish(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.dicionarioInglesService.buscarNoDicionario(palavra.toLowerCase(), SearchStructures.REED_BLACK);

        return ResponseEntity.ok(dicionarioResposta);
    }

    @GetMapping("/espanhol/avl/{palavra}")
    public ResponseEntity<DicionarioResposta> avlTreeSearchSpanish(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.dicionarioInglesService.buscarNoDicionario(palavra.toLowerCase(), SearchStructures.AVL);

        return ResponseEntity.ok(dicionarioResposta);
    }

    @GetMapping("/espanhol/hashmap/{palavra}")
    public ResponseEntity<DicionarioResposta> hashmapSearchSpanish(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.dicionarioInglesService.buscarNoDicionario(palavra.toLowerCase(), SearchStructures.HASHMAP);

        return ResponseEntity.ok(dicionarioResposta);
    }

    @GetMapping("/espanhol/array_list/{palavra}")
    public ResponseEntity<DicionarioResposta> arrayListSearchSpanish(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.dicionarioInglesService.buscarNoDicionario(palavra.toLowerCase(), SearchStructures.HASHMAP);

        return ResponseEntity.ok(dicionarioResposta);
    }
}
