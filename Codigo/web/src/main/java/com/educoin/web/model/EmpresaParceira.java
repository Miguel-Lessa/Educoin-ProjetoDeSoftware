package com.educoin.web.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "empresasParceiras")
public class EmpresaParceira {
    @Id
    private String cnpj;
    private String nome;
    private String senha;
    @OneToMany(mappedBy = "empresaParceira", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vantagem> vantagem;

}
