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

    private static final String ENGLISH_SUFIX = " english dictionary.txt";
    private static final String SPANISH_SUFIX = " spanish dictionary.txt";

    public SearchStruct getDicionarioComDadosDoArquivoParcial(Intervalo intervalo, Idiomas idioma, int pedacoAtual, SearchStructures tipoEstrutura , int numeroPedacos) {
        String nomeArquivo = this.getNomeArquivo(intervalo, idioma);
        String container = this.getNomeContainer(intervalo);
        SearchStruct dictionaryStructure = structuresFactory.getEstrutura(tipoEstrutura);
        long tamanho = this.azureBlobStorageRepository.getTamanhoArquivo(container, nomeArquivo);

        try(
                InputStream is = this.azureBlobStorageRepository
                        .baixarArquivoEmChunks(container, nomeArquivo,
                                (long) pedacoAtual * Math.toIntExact(tamanho / numeroPedacos + 1),
                                Math.toIntExact(tamanho / numeroPedacos + 1)
                        );
                Scanner leitor = new Scanner(is)
        )
        {
            while (leitor.hasNext()) this.processarLinha(idioma, leitor.nextLine(), dictionaryStructure);
        }
        catch (IOException exception) {
            System.out.println("Erro na leitura do arquivo e criação do Scanner");
        }
        return dictionaryStructure;
    }

    private void processarLinha(Idiomas idioma, String linha, SearchStruct dicionario) {
        try {
            Pair<String, List<String>> parsedLine;
            if(idioma.equals(Idiomas.ENGLISH)) parsedLine = this.englishParser.parseLine(linha);
            else parsedLine = this.spanishParser.parseLine(linha);

            dicionario.insert(parsedLine.getLeft(), parsedLine.getRight());
        } catch (IllegalArgumentException exception) {
            System.err.println(exception.getMessage());
        }
    }

    private String getNomeArquivo(Intervalo intervalo, Idiomas idioma) {
        if (Objects.requireNonNull(idioma) == Idiomas.ENGLISH) {
            return intervalo.getDescricao() + ENGLISH_SUFIX;
        }
        return intervalo.getDescricao() + SPANISH_SUFIX;
    }

    private String getNomeContainer(Intervalo intervalo) {
        return intervalo.getDescricao() + "-container";
    }

    public SearchStruct getDicionarioComDadosDoArquivoCompleto(Intervalo intervalo, Idiomas idioma,
                                                               SearchStructures tipoEstrutura) {
        String nomeArquivo = this.getNomeArquivo(intervalo, idioma);
        String container = this.getNomeContainer(intervalo);
        SearchStruct dictionaryStructure = structuresFactory.getEstrutura(tipoEstrutura);
        long tamanho = this.azureBlobStorageRepository.getTamanhoArquivo(container, nomeArquivo);

        try(
                InputStream is = this.azureBlobStorageRepository
                        .baixarArquivoEmChunks(container, nomeArquivo, 0, tamanho);
                Scanner leitor = new Scanner(is)
        )
        {
            while (leitor.hasNext()) this.processarLinha(idioma, leitor.nextLine(), dictionaryStructure);
        }
        catch (IOException exception) {
            System.out.println("Erro na leitura do arquivo e criação do Scanner");
        }
        return dictionaryStructure;
    }
}
