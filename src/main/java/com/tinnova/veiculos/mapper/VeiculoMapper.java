package com.tinnova.veiculos.mapper;

import com.tinnova.veiculos.records.VeiculoPatchRequest;
import com.tinnova.veiculos.records.VeiculoRequest;
import com.tinnova.veiculos.records.VeiculoResponse;
import com.tinnova.veiculos.domain.Veiculo;

public final class VeiculoMapper {

    private VeiculoMapper() {
    }

    public static Veiculo toEntity(VeiculoRequest veiculoRequest) {
        return Veiculo.builder()
                .veiculo(veiculoRequest.veiculo())
                .marca(veiculoRequest.marca())
                .cor(veiculoRequest.cor())
                .ano(veiculoRequest.ano())
                .descricao(veiculoRequest.descricao())
                .vendido(Boolean.TRUE.equals(veiculoRequest.vendido()))
                .build();
    }

    public static VeiculoResponse toResponse(Veiculo veiculo) {
        return new VeiculoResponse(
                veiculo.getId(), veiculo.getVeiculo(), veiculo.getMarca(), veiculo.getCor(), veiculo.getAno(),
                veiculo.getDescricao(), veiculo.isVendido(), veiculo.getCreated(), veiculo.getUpdated());
    }
}
