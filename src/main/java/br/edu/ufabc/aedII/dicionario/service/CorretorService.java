package br.edu.ufabc.aedII.dicionario.service;

import br.edu.ufabc.aedII.dicionario.controller.CorretorResposta;
import br.edu.ufabc.aedII.dicionario.model.SearchStruct;
import br.edu.ufabc.aedII.dicionario.model.SearchStructures;
import br.edu.ufabc.aedII.dicionario.repository.DictionaryRepository;
import br.edu.ufabc.aedII.dicionario.repository.Idiomas;
import br.edu.ufabc.aedII.dicionario.repository.Intervalo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class CorretorService {
    @Autowired
    private DictionaryRepository dictionaryRepository;

    public CorretorResposta executarCorretor(String palavraEntrada, SearchStructures tipoEstrutura, Idiomas idioma) {
        SearchStruct dicionarioAtoI = this.dictionaryRepository
                .getDicionarioComDadosDoArquivoCompleto(Intervalo.A_TO_I, idioma, tipoEstrutura);
        SearchStruct dicionarioJtoQ = this.dictionaryRepository
                .getDicionarioComDadosDoArquivoCompleto(Intervalo.J_TO_Q, idioma, tipoEstrutura);
        SearchStruct dicionarioRtoZ = this.dictionaryRepository
                .getDicionarioComDadosDoArquivoCompleto(Intervalo.R_TO_Z, idioma, tipoEstrutura);

        if (this.estaEscritaCorretamente(palavraEntrada, dicionarioAtoI, dicionarioJtoQ, dicionarioRtoZ))
            return new CorretorResposta(palavraEntrada, List.of("Palavra " + palavraEntrada + " escrita corretamente"));

        List<String> sugestoes = this.criarSugestoes(palavraEntrada.toLowerCase()).stream().sorted().toList();

        List<List<String>> intervalos = this.separarPorIntervalo(sugestoes);
        List<String> sugestoesPossiveis = new ArrayList<>();

        List<String> primeiroIntervalo = intervalos.get(0);
        List<String> segundoIntervalo = intervalos.get(1);
        List<String> terceiroIntervalo = intervalos.get(2);

        System.out.println("A LISTA DE SUGESTOES POSSUI " + sugestoes.size() + " PALAVRAS");
        System.out.println(sugestoes);
        for (String palavra : primeiroIntervalo)
            if(dicionarioAtoI.search(palavra) != null) sugestoesPossiveis.add(palavra);

        for (String palavra : segundoIntervalo)
            if(dicionarioJtoQ.search(palavra) != null) sugestoesPossiveis.add(palavra);

        for (String palavra : terceiroIntervalo)
            if(dicionarioRtoZ.search(palavra) != null) sugestoesPossiveis.add(palavra);
        ;
        return new CorretorResposta(palavraEntrada, sugestoesPossiveis);

    }

    private boolean estaEscritaCorretamente(String word, SearchStruct dicionario1,
                                            SearchStruct dicionario2, SearchStruct dicionario3) {
        return dicionario1.search(word) != null || dicionario2.search(word) != null || dicionario3.search(word) != null;
    }

    private List<List<String>> separarPorIntervalo(List<String> sugestoes) {

        List<List<String>> intervalos = new ArrayList<>();
        intervalos.add(sugestoes
                .stream()
                .filter(
                        s -> (int) 'a' <= (int) s.toLowerCase().charAt(0) && s.toLowerCase().charAt(0) <= (int) 'i')
                .toList());

        intervalos.add(sugestoes
                .stream()
                .filter(
                        s -> (int) 'j' <= (int) s.toLowerCase().charAt(0) && s.toLowerCase().charAt(0) <= (int) 'q')
                .toList());

        intervalos.add(sugestoes
                .stream()
                .filter(
                        s -> (int) 'r' <= (int) s.toLowerCase().charAt(0) && s.toLowerCase().charAt(0) <= (int) 'z')
                .toList());

        return intervalos;
    }

    private Set<String> criarSugestoes(String palavra) {
        Set<String> sugestoes = new HashSet<>();
        // adicionar uma letra em qualquer posição
        for (int i = 0; i <= palavra.length(); i++) {
            for (char letra = 'a'; letra <= 'z'; letra++) {
                StringBuilder sugestao = new StringBuilder(palavra);
                sugestao.insert(i, letra);
                sugestoes.add(sugestao.toString());
            }
        }

        // trocar um caractere em qualquer posição por outra letra do alfabeto
        for (int i = 0; i < palavra.length(); i++) {
            for (char letra = 'a'; letra <= 'z'; letra++) {
                StringBuilder sugestao = new StringBuilder(palavra);
                sugestao.setCharAt(i, letra);
                sugestoes.add(sugestao.toString());
            }
        }

        //trocar duas letras consecutivas
        for (int i = 0; i < palavra.length() - 1; i++) {
            StringBuilder sugestao = new StringBuilder(palavra);
            char letra1 = sugestao.charAt(i);
            char letra2 = sugestao.charAt(i + 1);
            sugestao.setCharAt(i, letra2);
            sugestao.setCharAt(i+1, letra1);
            sugestoes.add(sugestao.toString());
        }

        // deletar um caractere de qualquer posição da string
        for (int i = 0; i < palavra.length(); i++) {
            StringBuilder sugestao = new StringBuilder(palavra);
            sugestao.deleteCharAt(i);
            sugestoes.add(sugestao.toString());
        }
        List<String> semUltimaLetra = new ArrayList<>();
        // deletar um caractere de qualquer posição da string
        for (String s : sugestoes) {
            StringBuilder sugestao = new StringBuilder(s);
            semUltimaLetra.add(sugestao.deleteCharAt(s.length() - 1).toString());
        }
        sugestoes.addAll(semUltimaLetra);

        return sugestoes;
    }
}
