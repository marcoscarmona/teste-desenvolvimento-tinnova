package com.tinnova.veiculos.service;

import com.tinnova.veiculos.domain.Veiculo;
import com.tinnova.veiculos.records.VeiculoPatchRequest;
import com.tinnova.veiculos.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VeiculoService {

    private final VeiculoRepository repo;

    public Page<Veiculo> listar(String marca, String cor, Integer ano, Pageable pageable) {

        Specification<Veiculo> spec = Specification.where(null);

        if (StringUtils.isNotBlank(marca)) {
            spec = spec.and((root, q, cb) ->
                    cb.equal(cb.lower(root.get("marca")), marca.toLowerCase()));
        }

        if (ano != null) {
            spec = spec.and((root, q, cb) -> cb.equal(root.get("ano"), ano));
        }
        if (StringUtils.isNotBlank(cor)) {
            spec = spec.and((root, q, cb) ->
                    cb.equal(cb.lower(root.get("cor")), cor.toLowerCase()));
        }

        return repo.findAll(spec, pageable);
    }

    public Veiculo obter(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Veículo não encontrado"));
    }

    public Veiculo criar(Veiculo veiculo) {
        return repo.save(veiculo);
    }

    public Veiculo atualizar(Long id, Veiculo dados) {
        Veiculo veiculo = obter(id);
        veiculo.setVeiculo(dados.getVeiculo());
        veiculo.setMarca(dados.getMarca());
        veiculo.setCor(dados.getCor());
        veiculo.setAno(dados.getAno());
        veiculo.setDescricao(dados.getDescricao());
        veiculo.setVendido(dados.isVendido());
        return repo.save(veiculo);
    }

    public Veiculo atualizar(Long id, String descricao, Boolean vendido) {

        Veiculo veiculo = obter(id);

        if (vendido != null) {
            veiculo.setVendido(vendido);
        }

        if (StringUtils.isNotBlank(descricao)) {
            veiculo.setDescricao(descricao);
        }

        return repo.save(veiculo);
    }

    public void deletar(Long id) {
        repo.deleteById(id);
    }
}
