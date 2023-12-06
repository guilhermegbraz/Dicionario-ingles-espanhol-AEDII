package br.edu.ufabc.aedII.dicionario.controller;

import java.util.List;

public record SpellCheckerResponse(String word, List<String> suggestions) {
}
