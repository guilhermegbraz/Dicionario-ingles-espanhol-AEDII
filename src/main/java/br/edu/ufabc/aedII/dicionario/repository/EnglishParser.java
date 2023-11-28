package br.edu.ufabc.aedII.dicionario.repository;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;


@Component
public class EnglishParser {
    public Pair<String, List<String>> parseLine(String line) {
        try {
            String[] elementosLinha = line.split("\\|\\|");
            String palavra = elementosLinha[0].toLowerCase();
            List<String> definicaoDicionario = Arrays.asList(elementosLinha[1].split("%"));

            return Pair.of(palavra, definicaoDicionario);
        } catch (IndexOutOfBoundsException exception) {
            throw new IllegalArgumentException("Não foi possível parsear a linha: " + line);
        }
    }
}
