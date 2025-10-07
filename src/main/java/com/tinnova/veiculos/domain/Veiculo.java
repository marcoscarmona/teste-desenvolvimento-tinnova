package com.tinnova.veiculos.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "veiculo")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String veiculo;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String cor;

    @Column(nullable = false)
    private Integer ano;

    @Column(length = 1000)
    private String descricao;

    @Column(nullable = false)
    private boolean vendido;

    @Column(nullable = false, updatable = false)
    private Instant created;

    @Column(nullable = false)
    private Instant updated;

    @PrePersist
    void onCreate() {
        var now = Instant.now();
        created = now;
        updated = now;
    }

    @PreUpdate
    void onUpdate() {
        updated = Instant.now();
    }
}
