package com.tinnova.exercicios.exercicio3;


import java.util.Arrays;

public record Vetor(int[] valores) {

    public Vetor {
        if (valores == null) throw new IllegalArgumentException("O vetor não pode ser nulo.");
    }

    public int[] ordenarCom(SortAlgorithm algoritmo) {
        return algoritmo.sort(valores);
    }

    @Override
    public String toString() {
        return Arrays.toString(valores);
    }
}
