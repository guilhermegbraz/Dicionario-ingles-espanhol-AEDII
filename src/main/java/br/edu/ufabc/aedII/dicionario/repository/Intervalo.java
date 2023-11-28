package br.edu.ufabc.aedII.dicionario.repository;

public enum Intervalo {
    A_TO_I("a-i"),
    J_TO_Q("j-q"),
    R_TO_Z("r-z"),
    A_TO_Z("a-z");

    private final String descricao;

    Intervalo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
