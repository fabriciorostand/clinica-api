package com.alura.clinica.domain.paciente.dto;

import lombok.Getter;

@Getter
public class AtualizacaoPacienteRequest {
    private String nome;

    private String telefone;

    private String endereco;
}