package com.tinnnova.veiculos.service;

import com.tinnova.veiculos.domain.Veiculo;
import com.tinnova.veiculos.repository.VeiculoRepository;
import com.tinnova.veiculos.service.VeiculoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VeiculoServiceTest {

    @Mock
    private VeiculoRepository repo;

    @InjectMocks
    private VeiculoService service;

    private Veiculo veiculoBase;

    @BeforeEach
    void setup() {
        veiculoBase = new Veiculo();
        veiculoBase.setId(1L);
        veiculoBase.setVeiculo("Fusca");
        veiculoBase.setMarca("VW");
        veiculoBase.setCor("Azul");
        veiculoBase.setAno(1976);
        veiculoBase.setDescricao("Clássico");
        veiculoBase.setVendido(false);
    }

    @Test
    void deve_listar_todos() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").ascending());
        Page<Veiculo> page = new PageImpl<>(java.util.List.of(veiculoBase), pageable, 1);
        when(repo.findAll(any(Specification.class), eq(pageable))).thenReturn(page);
        Page<Veiculo> result = service.listar(null, null, null, pageable);
        assertEquals(1, result.getTotalElements());
        verify(repo).findAll(any(Specification.class), eq(pageable));
    }

    @Test
    void deve_listar_com_filtros() {
        Pageable pageable = PageRequest.of(0, 5);
        when(repo.findAll(any(Specification.class), eq(pageable)))
                .thenReturn(new PageImpl<>(java.util.List.of(veiculoBase)));
        Page<Veiculo> result = service.listar("VW", "Azul", 1976, pageable);
        assertEquals(1, result.getContent().size());
        verify(repo, times(1)).findAll(any(Specification.class), eq(pageable));
    }

    @Test
    void deve_buscar_por_id() {
        when(repo.findById(1L)).thenReturn(Optional.of(veiculoBase));
        Veiculo veiculo = service.obter(1L);
        assertNotNull(veiculo);
        assertEquals(1L, veiculo.getId());
        verify(repo).findById(1L);
    }

    @Test
    void deve_lancar_excecao_buscar_por_id() {
        when(repo.findById(99L)).thenReturn(Optional.empty());
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> service.obter(99L));
        assertEquals("Veículo não encontrado", ex.getMessage());
        verify(repo).findById(99L);
    }

    @Test
    void deve_criar_veiculo_novo() {
        when(repo.save(any(Veiculo.class))).thenAnswer(i -> i.getArgument(0));
        Veiculo salvo = service.criar(veiculoBase);
        assertEquals("Fusca", salvo.getVeiculo());
        verify(repo).save(veiculoBase);
    }

    @Test
    void deve_atualizar_veiculo_existente() {
        Veiculo veiculoAtualizado = new Veiculo();
        veiculoAtualizado.setVeiculo("Gol");
        veiculoAtualizado.setMarca("VW");
        veiculoAtualizado.setCor("Preto");
        veiculoAtualizado.setAno(2010);
        veiculoAtualizado.setDescricao("1.6 completo");
        veiculoAtualizado.setVendido(true);

        when(repo.findById(1L)).thenReturn(Optional.of(veiculoBase));
        when(repo.save(any(Veiculo.class))).thenAnswer(i -> i.getArgument(0));
        Veiculo atualizado = service.atualizar(1L, veiculoAtualizado);
        assertEquals("Gol", atualizado.getVeiculo());
        assertEquals("VW", atualizado.getMarca());
        assertEquals("Preto", atualizado.getCor());
        assertEquals(2010, atualizado.getAno());
        assertEquals("1.6 completo", atualizado.getDescricao());
        assertTrue(atualizado.isVendido());
    }

    @Test
    void deve_atualizar_descricao_e_vendido() {
        String descricao = "Vendido hoje";
        when(repo.findById(1L)).thenReturn(Optional.of(veiculoBase));
        when(repo.save(any(Veiculo.class))).thenAnswer(i -> i.getArgument(0));
        Veiculo result = service.atualizar(1L, descricao, true);
        assertTrue(result.isVendido());
        assertEquals("Vendido hoje", result.getDescricao());
        verify(repo).save(veiculoBase);
    }

    @Test
    void deve_deletar_veiculo_por_id() {
        service.deletar(1L);
        verify(repo).deleteById(1L);
    }
}
