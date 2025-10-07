package com.tinnova.exercicios.exercicio1;

public record Eleicao(int totalEleitores, int votosValidos, int votosBrancos, int votosNulos) {

    public double percentualValidos() {
        return calcularPercentual(votosValidos);
    }

    public double percentualBrancos() {
        return calcularPercentual(votosBrancos);
    }

    public double percentualNulos() {
        return calcularPercentual(votosNulos);
    }

    private double calcularPercentual(int votos) {
        if (totalEleitores == 0) {
            return 0;
        }
        return (double) votos / totalEleitores * 100.0;
    }

    @Override
    public String toString() {
        return String.format("""
                        Resultados:
                          Válidos: %.2f%%
                          Brancos: %.2f%%
                          Nulos:   %.2f%%
                        """,
                percentualValidos(), percentualBrancos(), percentualNulos());
    }
}
