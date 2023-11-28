package br.edu.ufabc.aedII.dicionario.model;

import br.edu.ufabc.aedII.dicionario.model.*;
import org.springframework.stereotype.Service;

@Service
public class StructuresFactory {

    public SearchStruct getEstrutura(SearchStructures structure) {
        switch (structure) {
            case TRIE -> {
                return new Trie();
            } case REED_BLACK -> {
                return new ReedBlackTree();
            } case AVL -> {
                return new AVLTree();
            } case HASHMAP -> {
                return new HashTableSearch();
            }
            default -> {
                return new ArrayListSearch();
            }
        }
    }
}

