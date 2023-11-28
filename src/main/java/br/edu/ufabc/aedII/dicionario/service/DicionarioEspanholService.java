package br.edu.ufabc.aedII.dicionario.service;

import br.edu.ufabc.aedII.dicionario.controller.DicionarioResposta;
import br.edu.ufabc.aedII.dicionario.model.SearchStruct;
import br.edu.ufabc.aedII.dicionario.model.SearchStructures;
import br.edu.ufabc.aedII.dicionario.model.StructuresFactory;
import br.edu.ufabc.aedII.dicionario.repository.AzureBlobStorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

@Service
public class DicionarioEspanholService {

    final static int NUMERO_DE_PEDACOS = 3;
    @Autowired
    private AzureBlobStorageRepository azureBlobStorageRepository;
    @Autowired
    private StructuresFactory structuresFactory;
    @Autowired
    private Scraper webScraper;

    public DicionarioResposta buscarNoDicionario(String palavraBuscada, SearchStructures tipoEstrutura) {
        float tempoTotal = 0f;
        int pedacoAtual = 0;
        boolean palavraEncontrada = false;
        SearchStruct estrutura = this.structuresFactory.getEstrutura(tipoEstrutura);

        while (pedacoAtual < NUMERO_DE_PEDACOS && !palavraEncontrada) {
            this.preencherEstruturaComDadosDoArquivo(palavraBuscada, pedacoAtual, estrutura);
            long inicio = System.nanoTime();
            List<String> palavra = estrutura.search(palavraBuscada);
            long fim = System.nanoTime();

            tempoTotal = (fim - inicio) / 1000f;

            if (palavra != null) palavraEncontrada = true;
            else pedacoAtual++;

            estrutura.clear();

            if (palavraEncontrada && !palavra.isEmpty()) {
                String definicao = this.webScraper.getDefinicao(palavraBuscada);
                return new DicionarioResposta(palavraBuscada, definicao, tempoTotal + " microssegundos");
            }
        }

        return new DicionarioResposta(palavraBuscada, "A palavra não foi encontrada", tempoTotal+ " microssegundos");
    }

    private void preencherEstruturaComDadosDoArquivo(String palavraBuscada, int pedacoAtual, SearchStruct arvoreDigital) {
        long tamanho = this.azureBlobStorageRepository
                .getTamanhoArquivo(this.getNomeContainer(palavraBuscada), this.getNomeArquivo(palavraBuscada));
        try(
                InputStream is = this.azureBlobStorageRepository
                        .baixarArquivoEmChunks(this.getNomeContainer(palavraBuscada),
                                this.getNomeArquivo(palavraBuscada),
                                (long) pedacoAtual * Math.toIntExact(tamanho / NUMERO_DE_PEDACOS + 1),
                                Math.toIntExact(tamanho / NUMERO_DE_PEDACOS + 1)
                        );
                Scanner leitor = new Scanner(is)
        )
        {
            while (leitor.hasNext()) {
                String palavra = leitor.nextLine();
                arvoreDigital.insert(palavra, List.of(palavra));
            }
        }
        catch (IOException exception) {
            System.out.println("Erro na leitura do arquivo e criação do Scanner");
        }
    }

    public String getNomeArquivo(String palavraBuscada) {
        return getIntervaloBusca(palavraBuscada) + " spanish dictionary.txt";
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
