package br.edu.ufabc.aedII.dicionario.model;

import java.util.List;

class TrieArrayNode {
    private final TrieArrayNode[] links;
    private boolean isEnd;

    private List<String> meaning;

    public TrieArrayNode() {
        this.links = new TrieArrayNode[26];
    }

    public TrieArrayNode getNode(char ch) {
        return links[ch - 'a'];
    }

    public void setNode(char ch, TrieArrayNode node) {
        links[ch - 'a'] = node;
    }

    public boolean containsKey(char ch) {
        return links[ch - 'a'] != null;
    }

    public void setEnd() {
        isEnd = true;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setMeaning(List<String> meaning) {
        this.meaning = meaning;
    }

    public List<String> getMeaning() {
        return meaning;
    }
}

public class TrieArray implements SearchStruct{
    private final TrieArrayNode root;

    public TrieArray() {
        root = new TrieArrayNode();
    }

    @Override
    public void insert(String word, List<String> meaning) {
        TrieArrayNode node = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.containsKey(ch)) {
                node.setNode(ch, new TrieArrayNode());
            }
            node = node.getNode(ch);
        }
        node.setEnd();
        node.setMeaning(meaning);
    }

    private TrieArrayNode searchPrefix(String word) {
        TrieArrayNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (node.containsKey(ch)) {
                node = node.getNode(ch);
            } else {
                return null;
            }
        }
        return node;
    }

    @Override
    public List<String> search(String word) {
        TrieArrayNode node = searchPrefix(word);
        if (node != null && node.isEnd()) return node.getMeaning();
        return null;
    }

    @Override
    public void clear() {

    }

    public boolean startsWith(String prefix) {
        TrieArrayNode node = searchPrefix(prefix);
        return node != null;
    }

}
