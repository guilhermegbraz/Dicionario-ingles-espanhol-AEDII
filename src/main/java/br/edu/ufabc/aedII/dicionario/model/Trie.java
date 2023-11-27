package br.edu.ufabc.aedII.dicionario.model;

import java.util.*;

class TrieNode {
    private final Map<Character, TrieNode> children;
    private boolean isEndOfWord;
    private List<String> meaning;

    public TrieNode() {
        this.children = new HashMap<>();
        this.isEndOfWord = false;
        this.meaning = null;
    }

    public Map<Character, TrieNode> getChildren() {
        return children;
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public void setEndOfWord(boolean endOfWord) {
        isEndOfWord = endOfWord;
    }

    public List<String> getMeaning() {
        return meaning;
    }

    public void setMeaning(List<String> meaning) {
        this.meaning = meaning;
    }
}

public class Trie implements SearchStruct{
    private final TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word, List<String> meaning) {
        TrieNode current = root;

        for (char ch : word.toCharArray()) {
            current.getChildren().computeIfAbsent(ch, c -> new TrieNode());
            current = current.getChildren().get(ch);
        }

        current.setEndOfWord(true);
        current.setMeaning(meaning);
    }

    public List<String> search(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            if (!current.getChildren().containsKey(ch)) {
                return null; // Palavra não encontrada
            }
            current = current.getChildren().get(ch);
        }

        return current.isEndOfWord() ? current.getMeaning() : null;
    }

    public void clear() {
        clearRecursively(root);
    }

    private void clearRecursively(TrieNode node) {
        if (node == null) {
            return;
        }

        Map<Character, TrieNode> children = node.getChildren();
        for (TrieNode child : children.values()) {
            clearRecursively(child);
        }

        // Clear current node
        node.getChildren().clear();
        node.setMeaning(null);
        node.setEndOfWord(false);
    }

    public void print() {
        printRecursively(root, new StringBuilder());
    }

    private void printRecursively(TrieNode node, StringBuilder prefix) {
        if (node == null) {
            return;
        }

        System.out.println(prefix.toString() + " -> " + (node.isEndOfWord() ? "End of Word" : ""));

        Map<Character, TrieNode> children = node.getChildren();
        for (Map.Entry<Character, TrieNode> entry : children.entrySet()) {
            char childChar = entry.getKey();
            TrieNode childNode = entry.getValue();

            prefix.append(childChar);
            printRecursively(childNode, prefix);
            prefix.deleteCharAt(prefix.length() - 1); // Backtrack
        }
    }
}
