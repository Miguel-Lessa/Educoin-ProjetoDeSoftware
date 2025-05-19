package com.educoin.web.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "alunos")
public class Aluno {

    @Id
    @Column(name = "rg", unique = true, nullable = false)
    private String rg;
    @Column(nullable = false)
    private String nome;
    private String endereco;
    private int saldoMoedas;
    @Column(nullable = false)
    private String senha;
    @Column(nullable = false)
    private String email;
    private String curso;
    @ManyToOne
    @JoinColumn(name = "instituicao_id", nullable = false)
    private Instituicao instituicao;
    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;
}
