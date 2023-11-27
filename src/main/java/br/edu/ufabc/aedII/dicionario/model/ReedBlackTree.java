package br.edu.ufabc.aedII.dicionario.model;

import java.util.List;
import java.util.TreeMap;

public class ReedBlackTree implements SearchStruct{

    private final TreeMap<String, List<String>> wordsTree;

    public ReedBlackTree() {
        this.wordsTree = new TreeMap<>();
    }

    @Override
    public void insert(String word, List<String> meaning) {
        this.wordsTree.put(word, meaning);
    }

    @Override
    public List<String> search(String word) {

        return this.wordsTree.get(word);
    }

    @Override
    public void clear() {
        this.wordsTree.clear();
    }
}
