package br.edu.ufabc.aedII.dicionario.service;

import br.edu.ufabc.aedII.dicionario.controller.SpellCheckerResponse;
import br.edu.ufabc.aedII.dicionario.model.SearchStruct;
import br.edu.ufabc.aedII.dicionario.model.SearchStructures;
import br.edu.ufabc.aedII.dicionario.repository.DictionaryRepository;
import br.edu.ufabc.aedII.dicionario.repository.Interval;
import br.edu.ufabc.aedII.dicionario.repository.Languages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class SpellCheckerService {
    @Autowired
    private DictionaryRepository dictionaryRepository;

    public SpellCheckerResponse run(String searchWord, SearchStructures structureType, Languages language) {
        SearchStruct dictionaryAtoI = this.dictionaryRepository
                .getDictionaryWithCompleteFileData(Interval.A_TO_I, language, structureType);
        SearchStruct dictionaryJtoQ = this.dictionaryRepository
                .getDictionaryWithCompleteFileData(Interval.J_TO_Q, language, structureType);
        SearchStruct dictionaryRtoZ = this.dictionaryRepository
                .getDictionaryWithCompleteFileData(Interval.R_TO_Z, language, structureType);

        if (this.isSpelledCorrectly(searchWord, dictionaryAtoI, dictionaryJtoQ, dictionaryRtoZ))
            return new SpellCheckerResponse(searchWord, List.of("Word " + searchWord + " spelled correctly"));

        List<String> suggestions = this.generateSuggestions(searchWord.toLowerCase()).stream().sorted().toList();

        List<List<String>> intervals = this.splitByInterval(suggestions);
        List<String> possibleSuggestions = new ArrayList<>();

        List<String> firstInterval = intervals.get(0);
        List<String> secondInterval = intervals.get(1);
        List<String> thirdInterval = intervals.get(2);

        System.out.println("THE SUGGESTIONS LIST HAS " + suggestions.size() + " WORDS");
        System.out.println(suggestions);
        for (String word : firstInterval)
            if(dictionaryAtoI.search(word) != null) possibleSuggestions.add(word);

        for (String word : secondInterval)
            if(dictionaryJtoQ.search(word) != null) possibleSuggestions.add(word);

        for (String word : thirdInterval)
            if(dictionaryRtoZ.search(word) != null) possibleSuggestions.add(word);
        ;
        return new SpellCheckerResponse(searchWord, possibleSuggestions);
    }

    private boolean isSpelledCorrectly(String word, SearchStruct dictionary1,
                                       SearchStruct dictionary2, SearchStruct dictionary3) {
        return dictionary1.search(word) != null || dictionary2.search(word) != null || dictionary3.search(word) != null;
    }

    private List<List<String>> splitByInterval(List<String> suggestions) {

        List<List<String>> intervals = new ArrayList<>();
        intervals.add(suggestions
                .stream()
                .filter(
                        s -> (int) 'a' <= (int) s.toLowerCase().charAt(0) && s.toLowerCase().charAt(0) <= (int) 'i')
                .toList());

        intervals.add(suggestions
                .stream()
                .filter(
                        s -> (int) 'j' <= (int) s.toLowerCase().charAt(0) && s.toLowerCase().charAt(0) <= (int) 'q')
                .toList());

        intervals.add(suggestions
                .stream()
                .filter(
                        s -> (int) 'r' <= (int) s.toLowerCase().charAt(0) && s.toLowerCase().charAt(0) <= (int) 'z')
                .toList());

        return intervals;
    }

    private Set<String> generateSuggestions(String word) {
        Set<String> suggestions = new HashSet<>();
        // add a letter at any position
        for (int i = 0; i <= word.length(); i++) {
            for (char letter = 'a'; letter <= 'z'; letter++) {
                StringBuilder suggestion = new StringBuilder(word);
                suggestion.insert(i, letter);
                suggestions.add(suggestion.toString());
            }
        }

        // replace a character at any position with another letter from the alphabet
        for (int i = 0; i < word.length(); i++) {
            for (char letter = 'a'; letter <= 'z'; letter++) {
                StringBuilder suggestion = new StringBuilder(word);
                suggestion.setCharAt(i, letter);
                suggestions.add(suggestion.toString());
            }
        }

        // swap two consecutive letters
        for (int i = 0; i < word.length() - 1; i++) {
            StringBuilder suggestion = new StringBuilder(word);
            char letter1 = suggestion.charAt(i);
            char letter2 = suggestion.charAt(i + 1);
            suggestion.setCharAt(i, letter2);
            suggestion.setCharAt(i+1, letter1);
            suggestions.add(suggestion.toString());
        }

        // delete a character from any position in the string
        for (int i = 0; i < word.length(); i++) {
            StringBuilder suggestion = new StringBuilder(word);
            suggestion.deleteCharAt(i);
            suggestions.add(suggestion.toString());
        }
        List<String> withoutLastLetter = new ArrayList<>();
        // delete a character from any position in the string
        for (String s : suggestions) {
            StringBuilder suggestion = new StringBuilder(s);
            withoutLastLetter.add(suggestion.deleteCharAt(s.length() - 1).toString());
        }
        suggestions.addAll(withoutLastLetter);

        return suggestions;
    }
}
