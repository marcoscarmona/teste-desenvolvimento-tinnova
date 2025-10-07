package com.tinnova.veiculos.records;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record VeiculoPatchRequest(
        @Size(max = 1000) String descricao,
        @NotNull Boolean vendido
) {
}
