package br.edu.ufabc.aedII.dicionario.controller;

import br.edu.ufabc.aedII.dicionario.model.SearchStructures;
import br.edu.ufabc.aedII.dicionario.repository.Idiomas;
import br.edu.ufabc.aedII.dicionario.service.BuscaDicionarioService;
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
    BuscaDicionarioService buscaDicionarioService;

    @GetMapping("/ingles/{palavra}")
    public ResponseEntity<DicionarioResposta> trieSearchEnglish(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.buscaDicionarioService.buscarNoDicionario(Idiomas.ENGLISH, palavra.toLowerCase(), SearchStructures.TRIE);

        return ResponseEntity.ok(dicionarioResposta);
    }

    @GetMapping("/ingles/red_black/{palavra}")
    public ResponseEntity<DicionarioResposta> redBlackTreeSearchEnglish(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.buscaDicionarioService.buscarNoDicionario(Idiomas.ENGLISH, palavra.toLowerCase(), SearchStructures.REED_BLACK);

        return ResponseEntity.ok(dicionarioResposta);
    }

    @GetMapping("/ingles/avl/{palavra}")
    public ResponseEntity<DicionarioResposta> avlTreeSearchEnglish(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.buscaDicionarioService.buscarNoDicionario(Idiomas.ENGLISH, palavra.toLowerCase(), SearchStructures.AVL);

        return ResponseEntity.ok(dicionarioResposta);
    }

    @GetMapping("/ingles/bst/{palavra}")
    public ResponseEntity<DicionarioResposta> bsTreeEnglish(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.buscaDicionarioService.buscarNoDicionario(Idiomas.ENGLISH, palavra.toLowerCase(), SearchStructures.BST);

        return ResponseEntity.ok(dicionarioResposta);
    }

    @GetMapping("/ingles/hashmap/{palavra}")
    public ResponseEntity<DicionarioResposta> hashmapSearchEnglish(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.buscaDicionarioService.buscarNoDicionario(Idiomas.ENGLISH, palavra.toLowerCase(), SearchStructures.HASHMAP);

        return ResponseEntity.ok(dicionarioResposta);
    }

    @GetMapping("/ingles/array_bs/{palavra}")
    public ResponseEntity<DicionarioResposta> arrayListSearchEnglish(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.buscaDicionarioService.buscarNoDicionario(Idiomas.ENGLISH, palavra.toLowerCase(), SearchStructures.HASHMAP);

        return ResponseEntity.ok(dicionarioResposta);
    }

    @GetMapping("/espanhol/{palavra}")
    public ResponseEntity<DicionarioResposta> trieSearchSpanish(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.buscaDicionarioService.buscarNoDicionario(Idiomas.SPANISH, palavra.toLowerCase(), SearchStructures.TRIE);

        return ResponseEntity.ok(dicionarioResposta);
    }

    @GetMapping("/espanhol/red_black/{palavra}")
    public ResponseEntity<DicionarioResposta> redBlackTreeSearchSpanish(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.buscaDicionarioService.buscarNoDicionario(Idiomas.SPANISH,palavra.toLowerCase(), SearchStructures.REED_BLACK);

        return ResponseEntity.ok(dicionarioResposta);
    }

    @GetMapping("/espanhol/avl/{palavra}")
    public ResponseEntity<DicionarioResposta> avlTreeSearchSpanish(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.buscaDicionarioService.buscarNoDicionario(Idiomas.SPANISH,palavra.toLowerCase(), SearchStructures.AVL);

        return ResponseEntity.ok(dicionarioResposta);
    }

    @GetMapping("/espanhol/bst/{palavra}")
    public ResponseEntity<DicionarioResposta> bsTreeSpanish(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.buscaDicionarioService.buscarNoDicionario(Idiomas.SPANISH, palavra.toLowerCase(), SearchStructures.BST);

        return ResponseEntity.ok(dicionarioResposta);
    }

    @GetMapping("/espanhol/hashmap/{palavra}")
    public ResponseEntity<DicionarioResposta> hashmapSearchSpanish(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.buscaDicionarioService.buscarNoDicionario(Idiomas.SPANISH,palavra.toLowerCase(), SearchStructures.HASHMAP);

        return ResponseEntity.ok(dicionarioResposta);
    }

    @GetMapping("/espanhol/array_list/{palavra}")
    public ResponseEntity<DicionarioResposta> arrayListSearchSpanish(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.buscaDicionarioService.buscarNoDicionario(Idiomas.SPANISH,palavra.toLowerCase(), SearchStructures.HASHMAP);

        return ResponseEntity.ok(dicionarioResposta);
    }

    @GetMapping("/completo/ingles/{palavra}")
    public ResponseEntity<DicionarioResposta> trieSearchEnglishCompleto(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.buscaDicionarioService.buscarNoDicionarioCompleto(Idiomas.ENGLISH, palavra.toLowerCase(), SearchStructures.TRIE);

        return ResponseEntity.ok(dicionarioResposta);
    }

    @GetMapping("/completo/ingles/red_black/{palavra}")
    public ResponseEntity<DicionarioResposta> redBlackTreeSearchEnglishCompleto(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.buscaDicionarioService.buscarNoDicionarioCompleto(Idiomas.ENGLISH, palavra.toLowerCase(), SearchStructures.REED_BLACK);

        return ResponseEntity.ok(dicionarioResposta);
    }

    @GetMapping("/completo/ingles/avl/{palavra}")
    public ResponseEntity<DicionarioResposta> avlTreeSearchEnglishCompleto(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.buscaDicionarioService.buscarNoDicionarioCompleto(Idiomas.ENGLISH, palavra.toLowerCase(), SearchStructures.AVL);

        return ResponseEntity.ok(dicionarioResposta);
    }

    @GetMapping("/completo/ingles/bst/{palavra}")
    public ResponseEntity<DicionarioResposta> bsTreeEnglishCompleto(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.buscaDicionarioService.buscarNoDicionario(Idiomas.ENGLISH, palavra.toLowerCase(), SearchStructures.BST);

        return ResponseEntity.ok(dicionarioResposta);
    }

    @GetMapping("/completo/ingles/hashmap/{palavra}")
    public ResponseEntity<DicionarioResposta> hashmapSearchEnglishCompleto(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.buscaDicionarioService.buscarNoDicionarioCompleto(Idiomas.ENGLISH, palavra.toLowerCase(), SearchStructures.HASHMAP);

        return ResponseEntity.ok(dicionarioResposta);
    }

    @GetMapping("/completo/ingles/array_list/{palavra}")
    public ResponseEntity<DicionarioResposta> arrayListSearchEnglishCompleto(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.buscaDicionarioService.buscarNoDicionarioCompleto(Idiomas.ENGLISH, palavra.toLowerCase(), SearchStructures.ARRAY_BS);

        return ResponseEntity.ok(dicionarioResposta);
    }

    @GetMapping("/completo/espanhol/{palavra}")
    public ResponseEntity<DicionarioResposta> trieSearchSpanishCompleto(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.buscaDicionarioService.buscarNoDicionarioCompleto(Idiomas.SPANISH, palavra.toLowerCase(), SearchStructures.TRIE);

        return ResponseEntity.ok(dicionarioResposta);
    }

    @GetMapping("/completo/espanhol/red_black/{palavra}")
    public ResponseEntity<DicionarioResposta> redBlackTreeSearchSpanishCompleto(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.buscaDicionarioService.buscarNoDicionarioCompleto(Idiomas.SPANISH, palavra.toLowerCase(), SearchStructures.REED_BLACK);

        return ResponseEntity.ok(dicionarioResposta);
    }

    @GetMapping("/completo/espanhol/avl/{palavra}")
    public ResponseEntity<DicionarioResposta> avlTreeSearchSpanishCompleto(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.buscaDicionarioService.buscarNoDicionarioCompleto(Idiomas.SPANISH, palavra.toLowerCase(), SearchStructures.AVL);

        return ResponseEntity.ok(dicionarioResposta);
    }

    @GetMapping("/completo/espanhol/bst/{palavra}")
    public ResponseEntity<DicionarioResposta> bsTreeSpanishCompleto(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.buscaDicionarioService.buscarNoDicionario(Idiomas.SPANISH, palavra.toLowerCase(), SearchStructures.BST);

        return ResponseEntity.ok(dicionarioResposta);
    }

    @GetMapping("/completo/espanhol/hashmap/{palavra}")
    public ResponseEntity<DicionarioResposta> hashmapSearchSpanishCompleto(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.buscaDicionarioService.buscarNoDicionarioCompleto(Idiomas.SPANISH, palavra.toLowerCase(), SearchStructures.HASHMAP);

        return ResponseEntity.ok(dicionarioResposta);
    }

    @GetMapping("/completo/espanhol/array_list/{palavra}")
    public ResponseEntity<DicionarioResposta> arrayListSearchSpanishCompleto(@PathVariable String palavra) {
        DicionarioResposta dicionarioResposta =
                this.buscaDicionarioService.buscarNoDicionarioCompleto(Idiomas.SPANISH, palavra.toLowerCase(), SearchStructures.ARRAY_BS);

        return ResponseEntity.ok(dicionarioResposta);
    }
}
