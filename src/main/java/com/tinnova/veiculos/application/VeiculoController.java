package com.tinnova.veiculos.application;

import com.tinnova.veiculos.domain.Veiculo;
import com.tinnova.veiculos.mapper.VeiculoMapper;
import com.tinnova.veiculos.records.VeiculoPatchRequest;
import com.tinnova.veiculos.records.VeiculoRequest;
import com.tinnova.veiculos.records.VeiculoResponse;
import com.tinnova.veiculos.service.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Veículos")
@RestController
@RequestMapping("/veiculos")
@RequiredArgsConstructor
public class VeiculoController {

    private final VeiculoService service;

    @Operation(summary = "Lista veículos")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<VeiculoResponse> listar(
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) String cor,
            @RequestParam(required = false) Integer ano,
            Pageable pageable) {

        Pageable pageableSortById = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Order.desc("id"))
        );

        return service.listar(marca, cor, ano, pageableSortById).map(VeiculoMapper::toResponse);
    }

    @Operation(summary = "Obtém veículo por id")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VeiculoResponse obter(@PathVariable Long id) {
        return VeiculoMapper.toResponse(service.obter(id));
    }

    @Operation(summary = "Cria novo veículo")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VeiculoResponse criar(@RequestBody @Valid VeiculoRequest req) {
        Veiculo salvo = service.criar(VeiculoMapper.toEntity(req));
        return VeiculoMapper.toResponse(salvo);
    }

    @Operation(summary = "Atualiza veículo")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VeiculoResponse atualizar(@PathVariable Long id, @RequestBody @Valid VeiculoRequest req) {
        Veiculo atualizado = service.atualizar(id, VeiculoMapper.toEntity(req));
        return VeiculoMapper.toResponse(atualizado);
    }

    @Operation(summary = "Atualiza veículo (descricao e vendido)")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VeiculoResponse atualizar(@PathVariable Long id, @RequestBody @Valid VeiculoPatchRequest req) {
        return VeiculoMapper.toResponse(service.atualizar(id, req.descricao(), req.vendido()));
    }

    @Operation(summary = "Remove veículo")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
