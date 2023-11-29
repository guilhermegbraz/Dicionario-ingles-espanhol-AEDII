package br.edu.ufabc.aedII.dicionario.model;

import java.util.List;

public class BinarySearchTree implements SearchStruct {

    private NodeDictionary root;

    @Override
    public void insert(String word, List<String> meaning) {
        this.root = this.insert(this.root, word, meaning);
    }

    private NodeDictionary insert(NodeDictionary root, String key, List<String> value) {
        if (root != null) {
            if (root.key.compareTo(key) > 0) root.setLeft(this.insert(root.getLeft(), key, value));
            else if (root.key.compareTo(key) < 0) root.setRight(this.insert(root.getRight(), key, value));
            return root;
        }

        return new NodeDictionary(key, value);
    }

    @Override
    public List<String> search(String word) {
        NodeDictionary nodeDictionary = this.search(this.root, word);
        if (nodeDictionary != null) return nodeDictionary.value;

        return null;
    }

    private NodeDictionary search(NodeDictionary root, String word) {
        if (root != null) {
            if (root.key.equals(word)) return root;
            else if (root.key.compareTo(word) > 0) return this.search(root.left, word);
            else if (root.key.compareTo(word) < 0) return this.search(root.right, word);
        }

        return root;
    }

    @Override
    public void clear() {
        this.clear(this.root);
    }

    private void clear(NodeDictionary root) {
        if (root != null) {
            clear(root.getLeft());
            clear(root.getRight());
            root.value = null;
        }
    }
}
