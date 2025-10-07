package com.tinnova.exercicios.exercicio4;

import java.util.Scanner;

public class Exercicio4SomaMultiplosMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o limite: ");
        int limite = sc.nextInt();
        Somador somador = new SomadorDeMultiplosDeTresECinco();
        int resultado = somador.calcular(limite);
        System.out.println("Soma dos múltiplos de 3 e 5 abaixo de " + limite + " = " + resultado);
    }
}
