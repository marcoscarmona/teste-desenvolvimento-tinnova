package com.tinnova.exercicios.exercicio2;

import java.math.BigInteger;
import java.util.Scanner;

public class Exercicio2FatorialMain {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        FatorialTemplate fatorial = new CalcFatorialRecursivo();
        System.out.println("Digite um número inteiro não negativo:");

        while (true) {
            System.out.print("Número: ");
            int numero = scanner.nextInt();
            if (numero < 0) {
                System.out.println("Encerrando o programa.");
                break;
            }
            try {
                BigInteger resultado = fatorial.calcular(numero);
                System.out.printf("Fatorial de %d = %s%n%n", numero, resultado);
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        scanner.close();
    }
}
