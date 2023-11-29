package br.edu.ufabc.aedII.dicionario.model;

import java.util.List;

public class NodeDictionary {
    String key;
    List<String> value;
    int height;
    NodeDictionary left, right;

    NodeDictionary(String k, List<String> v) {
        key = k;
        value = v;
        height = 1;
    }
    public void setLeft(NodeDictionary left) {
        this.left = left;
    }

    public void setRight(NodeDictionary right) {
        this.right = right;
    }

    public NodeDictionary getLeft() {
        return left;
    }

    public NodeDictionary getRight() {
        return right;
    }


}
