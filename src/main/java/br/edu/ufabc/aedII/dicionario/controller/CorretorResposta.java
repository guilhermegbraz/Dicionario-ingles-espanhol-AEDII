package br.edu.ufabc.aedII.dicionario.controller;

import java.util.List;

public record CorretorResposta(String palavraEntrada, List<String> sugerstoes) {
}
