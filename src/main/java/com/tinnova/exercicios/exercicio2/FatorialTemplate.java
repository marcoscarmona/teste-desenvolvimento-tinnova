package com.tinnova.exercicios.exercicio2;

import java.math.BigInteger;

public abstract class FatorialTemplate {

    public final BigInteger calcular(int numero) {
        validar(numero);
        return calcularFatorial(numero);
    }

    protected void validar(int numero) {
        if (numero < 0) throw new IllegalArgumentException("Número inválido.");
    }

    protected abstract BigInteger calcularFatorial(int numero);
}
