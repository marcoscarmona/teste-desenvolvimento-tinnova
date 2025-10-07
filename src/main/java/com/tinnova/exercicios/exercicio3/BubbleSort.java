package com.tinnova.exercicios.exercicio3;

import java.util.Arrays;

public class BubbleSort implements SortAlgorithm {

    @Override
    public int[] sort(int[] numeros) {

        int[] ordenados = Arrays.copyOf(numeros, numeros.length);

        for (int rodada = 0; rodada < ordenados.length - 1; rodada++) {

            for (int atual = 0; atual < ordenados.length - 1 - rodada; atual++) {
                int proximo = atual + 1;
                boolean precisaTrocar = ordenados[atual] > ordenados[proximo];
                if (precisaTrocar) {
                    trocar(ordenados, atual, proximo);
                }
            }
        }

        return ordenados;
    }

    private void trocar(int[] numeros, int atual, int proximo) {
        int temp = numeros[atual];
        numeros[atual] = numeros[proximo];
        numeros[proximo] = temp;
    }
}
