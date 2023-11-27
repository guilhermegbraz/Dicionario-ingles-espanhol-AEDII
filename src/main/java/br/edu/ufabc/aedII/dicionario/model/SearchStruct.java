package br.edu.ufabc.aedII.dicionario.model;

import java.util.List;

public interface SearchStruct {
    void insert(String word, List<String> meaning);

    List<String> search(String word);

    void clear();
}
