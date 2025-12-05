package com.alura.clinica.dto.medico;

import lombok.Getter;

@Getter
public class AtualizacaoMedicoRequest {
    private String nome;

    private String telefone;

    private String endereco;
}