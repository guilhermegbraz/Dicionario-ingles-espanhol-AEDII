package br.edu.ufabc.aedII.dicionario.model;
import edu.princeton.cs.algorithms.RedBlackBST;
import java.util.List;


public class ReedBlackTree implements SearchStruct{



    private RedBlackBST<String, List<String>> dicionario;

    public ReedBlackTree() {

        this.dicionario = new RedBlackBST<>();
    }

    @Override
    public void insert(String word, List<String> meaning) {

        this.dicionario.put(word, meaning);
    }

    @Override
    public List<String> search(String word) {

        return this.dicionario.get(word);
    }

    @Override
    public void clear() {
        this.dicionario = new RedBlackBST<>();

    }
}
