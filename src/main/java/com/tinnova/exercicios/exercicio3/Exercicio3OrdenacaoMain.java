package com.tinnova.exercicios.exercicio3;

import java.util.Arrays;

public class Exercicio3OrdenacaoMain {
    public static void main(String[] args) {
        int[] numeros = {5, 3, 2, 4, 7, 1, 0, 6};
        Vetor vetor = new Vetor(numeros);
        SortAlgorithm algoritmo = new BubbleSort();
        int[] ordenado = vetor.ordenarCom(algoritmo);
        System.out.println("Original: " + Arrays.toString(numeros));
        System.out.println("Ordenado: " + Arrays.toString(ordenado));
    }
}
