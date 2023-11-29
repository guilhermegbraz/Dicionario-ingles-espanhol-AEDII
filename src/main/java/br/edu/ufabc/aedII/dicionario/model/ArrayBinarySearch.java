package br.edu.ufabc.aedII.dicionario.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ArrayBinarySearch implements SearchStruct {


    static class DictionaryWord implements Comparable<DictionaryWord> {
        private final String word;
        private final List<String> meaning;

        public DictionaryWord(String word, List<String> meaning) {
            this.word = word;
            this.meaning = meaning;
        }

        public String getWord() {
            return word;
        }

        public List<String> getMeaning() {
            return meaning;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof DictionaryWord that)) return false;
            return Objects.equals(getWord(), that.getWord());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getWord());
        }


        @Override
        public int compareTo(DictionaryWord word2) {
            return this.word.compareTo(word2.getWord());
        }
    }
    private final List<DictionaryWord> words = new ArrayList<>();


    @Override
    public void insert(String word, List<String> meaning) {
        this.words.add(new DictionaryWord(word, meaning));
    }

    @Override
    public List<String> search(String word) {
//        int index = this.words.indexOf(new DictionaryWord(word, null));
//        if (index != -1) return this.words.get(index).getMeaning();
        this.words.sort(DictionaryWord::compareTo);
        int inicio = 0;
        int fim = this.words.size();

        while (inicio < fim) {
            int meio = inicio + (fim - inicio)/2;
            if(this.words.get(meio).word.equals(word)) return this.words.get(meio).meaning;

            else if (this.words.get(meio).word.compareTo(word) > 0) inicio = meio + 1;

            else fim = meio - 1;
        }

        return null;
    }

    @Override
    public void clear() {
        this.words.clear();
    }
}
