package com.tinnova.exercicios.exercicio4;

public class SomadorDeMultiplosDeTresECinco implements Somador {

    @Override
    public int calcular(int limite) {

        if (limite <= 0) {
            throw new IllegalArgumentException("O limite deve ser positivo.");
        }

        int soma = 0;

        for (int count = 1; count < limite; count++) {
            if (count % 3 == 0 || count % 5 == 0) {
                soma += count;
            }
        }

        return soma;
    }
}
