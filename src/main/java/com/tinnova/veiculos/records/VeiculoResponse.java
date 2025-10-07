package com.tinnova.veiculos.records;

import java.time.Instant;

public record VeiculoResponse(
        Long id,
        String veiculo,
        String marca,
        String cor,
        Integer ano,
        String descricao,
        boolean vendido,
        Instant created,
        Instant updated
) {
}
