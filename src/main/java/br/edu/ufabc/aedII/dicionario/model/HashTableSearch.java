package br.edu.ufabc.aedII.dicionario.model;

import java.util.Hashtable;
import java.util.List;

public class HashTableSearch implements SearchStruct {
    private final Hashtable<String, List<String>> words = new Hashtable<>();
    @Override
    public void insert(String word, List<String> meaning) {
        this.words.put(word, meaning);
    }

    @Override
    public List<String> search(String word) {
        return this.words.get(word);
    }

    @Override
    public void clear() {
        this.words.clear();
    }


}
