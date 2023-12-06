package br.edu.ufabc.aedII.dicionario.repository;

public enum Interval {
    A_TO_I("a-i"),
    J_TO_Q("j-q"),
    R_TO_Z("r-z"),
    A_TO_Z("a-z");

    private final String description;

    Interval(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
