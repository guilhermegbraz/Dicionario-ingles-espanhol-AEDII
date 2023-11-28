package br.edu.ufabc.aedII.dicionario.repository;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class SpanishParser {
    public Pair<String, List<String>> parseLine(String linha) {

        return Pair.of(linha.toLowerCase().trim(), Collections.singletonList(linha.toLowerCase().trim()));
    }
}
