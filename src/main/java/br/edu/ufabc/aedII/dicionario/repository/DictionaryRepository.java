package br.edu.ufabc.aedII.dicionario.repository;

import br.edu.ufabc.aedII.dicionario.model.SearchStruct;
import br.edu.ufabc.aedII.dicionario.model.SearchStructures;
import br.edu.ufabc.aedII.dicionario.model.StructuresFactory;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@Repository
public class DictionaryRepository {

    @Autowired
    private AzureBlobStorageRepository azureBlobStorageRepository;

    @Autowired
    private StructuresFactory structuresFactory;

    @Autowired
    private EnglishParser englishParser;

    @Autowired
    private SpanishParser spanishParser;

    private static final String ENGLISH_SUFFIX = " english dictionary.txt";
    private static final String SPANISH_SUFFIX = " spanish dictionary.txt";

    public SearchStruct getDictionaryWithPartialFileData(Interval interval, Languages language, int currentChunk, SearchStructures structureType, int numberOfChunks) {
        String fileName = this.getFileName(interval, language);
        String container = this.getContainerName(interval);
        SearchStruct dictionaryStructure = structuresFactory.getStructure(structureType);
        long size = this.azureBlobStorageRepository.getFileSize(container, fileName);

        try (
                InputStream is = this.azureBlobStorageRepository
                        .downloadFileInChunks(container, fileName,
                                (long) currentChunk * Math.toIntExact(size / numberOfChunks + 1),
                                Math.toIntExact(size / numberOfChunks + 1)
                        );
                Scanner reader = new Scanner(is)
        ) {
            while (reader.hasNext()) this.processLine(language, reader.nextLine(), dictionaryStructure);
        } catch (IOException exception) {
            System.out.println("Error reading file and creating Scanner");
        }
        return dictionaryStructure;
    }

    private void processLine(Languages language, String line, SearchStruct dictionary) {
        try {
            Pair<String, List<String>> parsedLine;
            if (language.equals(Languages.ENGLISH)) parsedLine = this.englishParser.parseLine(line);
            else parsedLine = this.spanishParser.parseLine(line);

            dictionary.insert(parsedLine.getLeft(), parsedLine.getRight());
        } catch (IllegalArgumentException exception) {
            System.err.println(exception.getMessage());
        }
    }

    private String getFileName(Interval interval, Languages language) {
        if (Objects.requireNonNull(language) == Languages.ENGLISH) {
            return interval.getDescription() + ENGLISH_SUFFIX;
        }
        return interval.getDescription() + SPANISH_SUFFIX;
    }

    private String getContainerName(Interval interval) {
        return interval.getDescription() + "-container";
    }

    public SearchStruct getDictionaryWithCompleteFileData(Interval interval, Languages language, SearchStructures structureType) {
        String fileName = this.getFileName(interval, language);
        String container = this.getContainerName(interval);
        SearchStruct dictionaryStructure = structuresFactory.getStructure(structureType);
        long size = this.azureBlobStorageRepository.getFileSize(container, fileName);

        try (
                InputStream is = this.azureBlobStorageRepository
                        .downloadFileInChunks(container, fileName, 0, size);
                Scanner reader = new Scanner(is)
        ) {
            while (reader.hasNext()) this.processLine(language, reader.nextLine(), dictionaryStructure);
        } catch (IOException exception) {
            System.out.println("Error reading file and creating Scanner");
        }
        return dictionaryStructure;
    }
}
