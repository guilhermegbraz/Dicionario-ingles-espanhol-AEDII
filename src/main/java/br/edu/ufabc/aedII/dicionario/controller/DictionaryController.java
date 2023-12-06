package br.edu.ufabc.aedII.dicionario.controller;

import br.edu.ufabc.aedII.dicionario.model.SearchStructures;
import br.edu.ufabc.aedII.dicionario.repository.Languages;
import br.edu.ufabc.aedII.dicionario.service.DictionarySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(("/dicionario"))
public class DictionaryController {

    @Autowired
    DictionarySearchService dictionarySearchService;

    @GetMapping("/ingles/{palavra}")
    public ResponseEntity<DictionaryResponse> trieSearchEnglish(@PathVariable String palavra) {
        DictionaryResponse dictionaryResponse =
                this.dictionarySearchService.searchInDictionary(Languages.ENGLISH, palavra.toLowerCase(), SearchStructures.TRIE);

        return ResponseEntity.ok(dictionaryResponse);
    }

    @GetMapping("/ingles/red_black/{palavra}")
    public ResponseEntity<DictionaryResponse> redBlackTreeSearchEnglish(@PathVariable String palavra) {
        DictionaryResponse dictionaryResponse =
                this.dictionarySearchService.searchInDictionary(Languages.ENGLISH, palavra.toLowerCase(), SearchStructures.REED_BLACK);

        return ResponseEntity.ok(dictionaryResponse);
    }

    @GetMapping("/ingles/avl/{palavra}")
    public ResponseEntity<DictionaryResponse> avlTreeSearchEnglish(@PathVariable String palavra) {
        DictionaryResponse dictionaryResponse =
                this.dictionarySearchService.searchInDictionary(Languages.ENGLISH, palavra.toLowerCase(), SearchStructures.AVL);

        return ResponseEntity.ok(dictionaryResponse);
    }

    @GetMapping("/ingles/bst/{palavra}")
    public ResponseEntity<DictionaryResponse> bsTreeEnglish(@PathVariable String palavra) {
        DictionaryResponse dictionaryResponse =
                this.dictionarySearchService.searchInDictionary(Languages.ENGLISH, palavra.toLowerCase(), SearchStructures.BST);

        return ResponseEntity.ok(dictionaryResponse);
    }

    @GetMapping("/ingles/hashmap/{palavra}")
    public ResponseEntity<DictionaryResponse> hashmapSearchEnglish(@PathVariable String palavra) {
        DictionaryResponse dictionaryResponse =
                this.dictionarySearchService.searchInDictionary(Languages.ENGLISH, palavra.toLowerCase(), SearchStructures.HASHMAP);

        return ResponseEntity.ok(dictionaryResponse);
    }

    @GetMapping("/ingles/array_bs/{palavra}")
    public ResponseEntity<DictionaryResponse> arrayListSearchEnglish(@PathVariable String palavra) {
        DictionaryResponse dictionaryResponse =
                this.dictionarySearchService.searchInDictionary(Languages.ENGLISH, palavra.toLowerCase(), SearchStructures.ARRAY_BS);

        return ResponseEntity.ok(dictionaryResponse);
    }

    @GetMapping("/espanhol/{palavra}")
    public ResponseEntity<DictionaryResponse> trieSearchSpanish(@PathVariable String palavra) {
        DictionaryResponse dictionaryResponse =
                this.dictionarySearchService.searchInDictionary(Languages.SPANISH, palavra.toLowerCase(), SearchStructures.TRIE);

        return ResponseEntity.ok(dictionaryResponse);
    }

    @GetMapping("/espanhol/red_black/{palavra}")
    public ResponseEntity<DictionaryResponse> redBlackTreeSearchSpanish(@PathVariable String palavra) {
        DictionaryResponse dictionaryResponse =
                this.dictionarySearchService.searchInDictionary(Languages.SPANISH,palavra.toLowerCase(), SearchStructures.REED_BLACK);

        return ResponseEntity.ok(dictionaryResponse);
    }

    @GetMapping("/espanhol/avl/{palavra}")
    public ResponseEntity<DictionaryResponse> avlTreeSearchSpanish(@PathVariable String palavra) {
        DictionaryResponse dictionaryResponse =
                this.dictionarySearchService.searchInDictionary(Languages.SPANISH,palavra.toLowerCase(), SearchStructures.AVL);

        return ResponseEntity.ok(dictionaryResponse);
    }

    @GetMapping("/espanhol/bst/{palavra}")
    public ResponseEntity<DictionaryResponse> bsTreeSpanish(@PathVariable String palavra) {
        DictionaryResponse dictionaryResponse =
                this.dictionarySearchService.searchInDictionary(Languages.SPANISH, palavra.toLowerCase(), SearchStructures.BST);

        return ResponseEntity.ok(dictionaryResponse);
    }

    @GetMapping("/espanhol/hashmap/{palavra}")
    public ResponseEntity<DictionaryResponse> hashmapSearchSpanish(@PathVariable String palavra) {
        DictionaryResponse dictionaryResponse =
                this.dictionarySearchService.searchInDictionary(Languages.SPANISH,palavra.toLowerCase(), SearchStructures.HASHMAP);

        return ResponseEntity.ok(dictionaryResponse);
    }

    @GetMapping("/espanhol/array_bs/{palavra}")
    public ResponseEntity<DictionaryResponse> arrayListSearchSpanish(@PathVariable String palavra) {
        DictionaryResponse dictionaryResponse =
                this.dictionarySearchService.searchInDictionary(Languages.SPANISH,palavra.toLowerCase(), SearchStructures.ARRAY_BS);

        return ResponseEntity.ok(dictionaryResponse);
    }

    @GetMapping("/completo/ingles/{palavra}")
    public ResponseEntity<DictionaryResponse> trieSearchEnglishCompleto(@PathVariable String palavra) {
        DictionaryResponse dictionaryResponse =
                this.dictionarySearchService.searchInCompleteDictionary(Languages.ENGLISH, palavra.toLowerCase(), SearchStructures.TRIE);

        return ResponseEntity.ok(dictionaryResponse);
    }

    @GetMapping("/completo/ingles/red_black/{palavra}")
    public ResponseEntity<DictionaryResponse> redBlackTreeSearchEnglishCompleto(@PathVariable String palavra) {
        DictionaryResponse dictionaryResponse =
                this.dictionarySearchService.searchInCompleteDictionary(Languages.ENGLISH, palavra.toLowerCase(), SearchStructures.REED_BLACK);

        return ResponseEntity.ok(dictionaryResponse);
    }

    @GetMapping("/completo/ingles/avl/{palavra}")
    public ResponseEntity<DictionaryResponse> avlTreeSearchEnglishCompleto(@PathVariable String palavra) {
        DictionaryResponse dictionaryResponse =
                this.dictionarySearchService.searchInCompleteDictionary(Languages.ENGLISH, palavra.toLowerCase(), SearchStructures.AVL);

        return ResponseEntity.ok(dictionaryResponse);
    }

    @GetMapping("/completo/ingles/bst/{palavra}")
    public ResponseEntity<DictionaryResponse> bsTreeEnglishCompleto(@PathVariable String palavra) {
        DictionaryResponse dictionaryResponse =
                this.dictionarySearchService.searchInCompleteDictionary(Languages.ENGLISH, palavra.toLowerCase(), SearchStructures.BST);

        return ResponseEntity.ok(dictionaryResponse);
    }

    @GetMapping("/completo/ingles/hashmap/{palavra}")
    public ResponseEntity<DictionaryResponse> hashmapSearchEnglishCompleto(@PathVariable String palavra) {
        DictionaryResponse dictionaryResponse =
                this.dictionarySearchService.searchInCompleteDictionary(Languages.ENGLISH, palavra.toLowerCase(), SearchStructures.HASHMAP);

        return ResponseEntity.ok(dictionaryResponse);
    }

    @GetMapping("/completo/ingles/array_bs/{palavra}")
    public ResponseEntity<DictionaryResponse> arrayListSearchEnglishCompleto(@PathVariable String palavra) {
        DictionaryResponse dictionaryResponse =
                this.dictionarySearchService.searchInCompleteDictionary(Languages.ENGLISH, palavra.toLowerCase(), SearchStructures.ARRAY_BS);

        return ResponseEntity.ok(dictionaryResponse);
    }

    @GetMapping("/completo/espanhol/{palavra}")
    public ResponseEntity<DictionaryResponse> trieSearchSpanishCompleto(@PathVariable String palavra) {
        DictionaryResponse dictionaryResponse =
                this.dictionarySearchService.searchInCompleteDictionary(Languages.SPANISH, palavra.toLowerCase(), SearchStructures.TRIE);

        return ResponseEntity.ok(dictionaryResponse);
    }

    @GetMapping("/completo/espanhol/red_black/{palavra}")
    public ResponseEntity<DictionaryResponse> redBlackTreeSearchSpanishCompleto(@PathVariable String palavra) {
        DictionaryResponse dictionaryResponse =
                this.dictionarySearchService.searchInCompleteDictionary(Languages.SPANISH, palavra.toLowerCase(), SearchStructures.REED_BLACK);

        return ResponseEntity.ok(dictionaryResponse);
    }

    @GetMapping("/completo/espanhol/avl/{palavra}")
    public ResponseEntity<DictionaryResponse> avlTreeSearchSpanishCompleto(@PathVariable String palavra) {
        DictionaryResponse dictionaryResponse =
                this.dictionarySearchService.searchInCompleteDictionary(Languages.SPANISH, palavra.toLowerCase(), SearchStructures.AVL);

        return ResponseEntity.ok(dictionaryResponse);
    }

    @GetMapping("/completo/espanhol/bst/{palavra}")
    public ResponseEntity<DictionaryResponse> bsTreeSpanishCompleto(@PathVariable String palavra) {
        DictionaryResponse dictionaryResponse =
                this.dictionarySearchService.searchInCompleteDictionary(Languages.SPANISH, palavra.toLowerCase(), SearchStructures.BST);

        return ResponseEntity.ok(dictionaryResponse);
    }

    @GetMapping("/completo/espanhol/hashmap/{palavra}")
    public ResponseEntity<DictionaryResponse> hashmapSearchSpanishCompleto(@PathVariable String palavra) {
        DictionaryResponse dictionaryResponse =
                this.dictionarySearchService.searchInCompleteDictionary(Languages.SPANISH, palavra.toLowerCase(), SearchStructures.HASHMAP);

        return ResponseEntity.ok(dictionaryResponse);
    }

    @GetMapping("/completo/espanhol/array_bs/{palavra}")
    public ResponseEntity<DictionaryResponse> arrayListSearchSpanishCompleto(@PathVariable String palavra) {
        DictionaryResponse dictionaryResponse =
                this.dictionarySearchService.searchInCompleteDictionary(Languages.SPANISH, palavra.toLowerCase(), SearchStructures.ARRAY_BS);

        return ResponseEntity.ok(dictionaryResponse);
    }
}
