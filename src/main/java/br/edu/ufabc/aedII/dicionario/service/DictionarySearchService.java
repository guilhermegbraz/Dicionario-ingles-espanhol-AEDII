package br.edu.ufabc.aedII.dicionario.service;

import br.edu.ufabc.aedII.dicionario.controller.DictionaryResponse;
import br.edu.ufabc.aedII.dicionario.model.SearchStruct;
import br.edu.ufabc.aedII.dicionario.model.SearchStructures;
import br.edu.ufabc.aedII.dicionario.repository.DictionaryRepository;
import br.edu.ufabc.aedII.dicionario.repository.Interval;
import br.edu.ufabc.aedII.dicionario.repository.Languages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class DictionarySearchService {

    private static final int NUMBER_OF_CHUNKS = 3;
    private static final String TEMPORAL_UNIT = "microseconds";

    @Autowired
    private DictionaryRepository dictionaryRepository;

    @Autowired
    private Scraper webScraper;

    public DictionaryResponse searchInDictionary(Languages language, String searchedWord, SearchStructures structureType) {
        String totalExecutionTime = "";
        int currentChunk = 0;

        while (currentChunk < NUMBER_OF_CHUNKS) {
            SearchStruct dictionary = this.dictionaryRepository.getDictionaryWithPartialFileData(
                    this.getSearchInterval(searchedWord),
                    language,
                    currentChunk,
                    structureType,
                    NUMBER_OF_CHUNKS
            );

            long startTime = System.nanoTime();
            List<String> definitions = dictionary.search(searchedWord);
            long endTime = System.nanoTime();

            totalExecutionTime = this.convertExecutionTime(startTime, endTime);

            if (definitions != null) {
                String wordDefinition = definitions.toString();
                dictionary.clear();
                if (language.equals(Languages.SPANISH)) wordDefinition = this.webScraper.getDefinition(searchedWord);

                return new DictionaryResponse(searchedWord, wordDefinition, totalExecutionTime);
            } else {
                currentChunk++;
            }

            dictionary.clear();
        }

        return new DictionaryResponse(searchedWord, "The word was not found", totalExecutionTime);
    }

    private Interval getSearchInterval(String searchedWord) {
        char firstLetter = searchedWord.toLowerCase().charAt(0);
        if ('a' <= firstLetter && firstLetter <= 'i') return Interval.A_TO_I;
        else if ('j' <= firstLetter && firstLetter <= 'q') return Interval.J_TO_Q;
        return Interval.R_TO_Z;
    }

    private String convertExecutionTime(long startNanoTime, long endNanoTime) {
        return (endNanoTime - startNanoTime) / 1000f + " " + TEMPORAL_UNIT;
    }

    public DictionaryResponse searchInCompleteDictionary(Languages language, String searchedWord, SearchStructures structureType) {
        SearchStruct dictionary = this.dictionaryRepository.getDictionaryWithCompleteFileData(Interval.A_TO_Z, language, structureType);

        long startTime = System.nanoTime();
        List<String> definitions = dictionary.search(searchedWord);
        long endTime = System.nanoTime();

        String totalExecutionTime = this.convertExecutionTime(startTime, endTime);

        if (definitions != null) {
            String wordDefinition = definitions.toString();
            dictionary.clear();
            if (language.equals(Languages.SPANISH)) wordDefinition = this.webScraper.getDefinition(searchedWord);

            return new DictionaryResponse(searchedWord, wordDefinition, totalExecutionTime);
        }

        dictionary.clear();

        return new DictionaryResponse(searchedWord, "The word was not found", totalExecutionTime);
    }
}
