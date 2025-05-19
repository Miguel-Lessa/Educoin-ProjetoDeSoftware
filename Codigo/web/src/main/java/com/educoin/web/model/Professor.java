package com.educoin.web.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "professores")
public class Professor {

    @Id
    @Column(name = "rg", unique = true, nullable = false)
    private String id;
    @Column(nullable = false)
    private String nome;
    private int saldoMoedas;
    private String departamento;
    @Column(nullable = false)
    private String senha;
    @Column(nullable = false)
    private String curso;
    @ManyToOne
    @JoinColumn(name = "instituicao_id", nullable = false)
    private Instituicao instituicao;
}