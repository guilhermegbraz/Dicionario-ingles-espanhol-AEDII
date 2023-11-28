package br.edu.ufabc.aedII.dicionario.service;

import br.edu.ufabc.aedII.dicionario.controller.DicionarioResposta;
import br.edu.ufabc.aedII.dicionario.model.SearchStruct;
import br.edu.ufabc.aedII.dicionario.model.SearchStructures;
import br.edu.ufabc.aedII.dicionario.repository.DictionaryRepository;
import br.edu.ufabc.aedII.dicionario.repository.Idiomas;
import br.edu.ufabc.aedII.dicionario.repository.Intervalo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BuscaDicionarioService {

    final static int NUMERO_DE_PEDACOS = 3;
    final static String UNINADE_DE_MEDIDA_TEMPORAL = "microsegundos";
    @Autowired
    DictionaryRepository dictionaryRepository;
    @Autowired
    private Scraper webScraper;

    public DicionarioResposta buscarNoDicionario(Idiomas idioma, String palavraBuscada, SearchStructures tipoEstrutura) {
        String tempoTotal = "";
        int pedacoAtual = 0;

        while (pedacoAtual < NUMERO_DE_PEDACOS ) {
            SearchStruct dicionario = this.dictionaryRepository.getDicionarioComDadosDoArquivoParcial
                    (
                    this.getIntervaloBusca(palavraBuscada),
                    idioma,
                    pedacoAtual,
                    tipoEstrutura,NUMERO_DE_PEDACOS
                );

            long inicio = System.nanoTime();
            List<String> definicoes = dicionario.search(palavraBuscada);
            long fim = System.nanoTime();

            tempoTotal = this.converterTempoExecucao(inicio, fim);

            if (definicoes != null) {
                String definicaoPalavra = definicoes.toString();
                dicionario.clear();
                if (idioma.equals(Idiomas.SPANISH)) definicaoPalavra = this.webScraper.getDefinicao(palavraBuscada);

                return new DicionarioResposta(palavraBuscada, definicaoPalavra, tempoTotal);
            }
            else pedacoAtual++;

            dicionario.clear();
        }

        return new DicionarioResposta(palavraBuscada, "A palavra não foi encontrada", tempoTotal);
    }


    private Intervalo getIntervaloBusca(String palavraBuscada) {
        char primeiraLetra = palavraBuscada.toLowerCase().charAt(0);
        if ((int) 'a' <= (int) primeiraLetra && primeiraLetra <= (int) 'i') return Intervalo.A_TO_I;
        else if ((int) 'j' <= (int) primeiraLetra && primeiraLetra <= (int) 'q') return Intervalo.J_TO_Q;
        return Intervalo.R_TO_Z;
    }

    private String converterTempoExecucao(long inicioNanoTime, long fimNanoTime) {
        return (fimNanoTime - inicioNanoTime) / 1000f + " " + UNINADE_DE_MEDIDA_TEMPORAL;
    }

    public DicionarioResposta buscarNoDicionarioCompleto(Idiomas idioma, String palavraBuscada, SearchStructures tipoEstrutura) {
        String tempoTotal = "";
        SearchStruct dicionario = this.dictionaryRepository
                .getDicionarioComDadosDoArquivoCompleto(Intervalo.A_TO_Z, idioma, tipoEstrutura);

        long inicio = System.nanoTime();
        List<String> definicoes = dicionario.search(palavraBuscada);
        long fim = System.nanoTime();

        tempoTotal = this.converterTempoExecucao(inicio, fim);

        if (definicoes != null) {
            String definicaoPalavra = definicoes.toString();
            dicionario.clear();
            if (idioma.equals(Idiomas.SPANISH)) definicaoPalavra = this.webScraper.getDefinicao(palavraBuscada);

            return new DicionarioResposta(palavraBuscada, definicaoPalavra, tempoTotal);
        }
        dicionario.clear();

        return new DicionarioResposta(palavraBuscada, "A palavra não foi encontrada", tempoTotal);
    }
}
