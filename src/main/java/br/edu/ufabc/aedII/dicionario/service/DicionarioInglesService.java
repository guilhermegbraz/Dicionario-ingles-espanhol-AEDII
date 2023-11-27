package br.edu.ufabc.aedII.dicionario.service;

import br.edu.ufabc.aedII.dicionario.controller.DicionarioResposta;
import br.edu.ufabc.aedII.dicionario.model.SearchStruct;
import br.edu.ufabc.aedII.dicionario.model.SearchStructures;
import br.edu.ufabc.aedII.dicionario.model.Trie;
import br.edu.ufabc.aedII.dicionario.repository.AzureBlobStorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Service
public class DicionarioInglesService {

    final static int NUMERO_DE_PEDACOS = 3;
    @Autowired
    private AzureBlobStorageRepository azureBlobStorageRepository;
    @Autowired
    private StructuresFactory structuresFactory;

    public DicionarioResposta buscarNoDicionario(String palavraBuscada, SearchStructures tipoEstrutura) {
        String nomeArquivo = this.getNomeArquivo(palavraBuscada);
        String container = this.getNomeContainer(palavraBuscada);
        long tamanho = this.azureBlobStorageRepository.getTamanhoArquivo(container, nomeArquivo);

        int pedacoAtual = 0;
        boolean palavraEncontrada = false;
        List<String> definicao = null;
        SearchStruct estrutura = this.structuresFactory.getEstrutura(tipoEstrutura);

        while (pedacoAtual < NUMERO_DE_PEDACOS && !palavraEncontrada) {
            this.preencherEstruturaComDadosDoArquivo(container, nomeArquivo, pedacoAtual, tamanho, estrutura);

            long inicio = System.nanoTime();
            definicao = estrutura.search(palavraBuscada);
            long fim = System.nanoTime();

            float tempoTotal = (fim - inicio) / 1000f;

            if (definicao != null) palavraEncontrada = true;
            else pedacoAtual++;

            estrutura.clear();

            if (palavraEncontrada && !definicao.isEmpty())
                return new DicionarioResposta(palavraBuscada, definicao.toString(),tempoTotal + " microssegundos");
        }

        return new DicionarioResposta(palavraBuscada, "A palavra não foi encontrada", "-");

    }

    private void preencherEstruturaComDadosDoArquivo(String container, String nomeArquivo, int pedacoAtual, long tamanho, SearchStruct arvoreDigital) {
        try(
                InputStream is = this.azureBlobStorageRepository
                        .baixarArquivoEmChunks(container, nomeArquivo,
                                (long) pedacoAtual * Math.toIntExact(tamanho / NUMERO_DE_PEDACOS + 1),
                                Math.toIntExact(tamanho / NUMERO_DE_PEDACOS + 1)
                        );
                Scanner leitor = new Scanner(is)
        )
        {
            while (leitor.hasNext()) this.processarLinha(leitor.nextLine(), arvoreDigital);
        }
        catch (IOException exception) {
            System.out.println("Erro na leitura do arquivo e criação do Scanner");
        }
    }

    private void processarLinha(String linha, SearchStruct arvoreDigital) {
        try {
            String[] elementosLinha = linha.split("\\|\\|");
            String palavra = elementosLinha[0];
            List<String> definicaoDicionario = Arrays.asList(elementosLinha[1].split("%"));
            arvoreDigital.insert(palavra, definicaoDicionario);
        } catch (IndexOutOfBoundsException exception) {
            System.err.println("Erro na criação da árvore, linha inválida: " + linha);
            System.err.println(exception.getMessage());
        }
    }

    public String getNomeArquivo(String palavraBuscada) {
        return getIntervaloBusca(palavraBuscada) + " english dictionary.txt";
    }

    public String getNomeContainer(String palavraBuscada) {
        return this.getIntervaloBusca(palavraBuscada) + "-container";
    }

    private String getIntervaloBusca(String palavraBuscada) {
        char primeiraLetra = palavraBuscada.toLowerCase().charAt(0);
        if ((int) 'a' <= (int) primeiraLetra && primeiraLetra <= (int) 'i') return "a-i";
        else if ((int) 'j' <= (int) primeiraLetra && primeiraLetra <= (int) 'q') return "j-q";
        return "r-z";
    }
}
