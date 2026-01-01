package com.alura.clinica.domain.medico.dto;

import lombok.Getter;

@Getter
public class AtualizacaoMedicoRequest {
    private String nome;

    private String telefone;

    private String endereco;
}