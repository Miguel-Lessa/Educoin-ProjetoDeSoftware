package com.educoin.web.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Vantagem {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private int custoMoeda;

    @ManyToOne
    @JoinColumn(name = "cnpj")
    private EmpresaParceira empresaParceira;

}
