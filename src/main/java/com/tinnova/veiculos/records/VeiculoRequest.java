package com.tinnova.veiculos.records;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record VeiculoRequest(
        @NotBlank String veiculo,
        @NotBlank String marca,
        @NotBlank String cor,
        @NotNull Integer ano,
        @Size(max = 1000) String descricao,
        @NotNull Boolean vendido
) {
}
