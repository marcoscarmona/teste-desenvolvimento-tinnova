package com.tinnova.exercicios.exercicio2;

import java.math.BigInteger;

public class CalcFatorialRecursivo extends FatorialTemplate {

    @Override
    protected BigInteger calcularFatorial(int numero) {

        if (numero <= 1) {
            return BigInteger.ONE;
        }
        return BigInteger.valueOf(numero)
                .multiply(calcularFatorial(numero - 1));
    }
}
