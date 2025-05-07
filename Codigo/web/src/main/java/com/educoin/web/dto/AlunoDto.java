package com.educoin.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlunoDto {
    private String rg;
    private String endereco;
    private int saldoMoedas;
    private String senha;
}