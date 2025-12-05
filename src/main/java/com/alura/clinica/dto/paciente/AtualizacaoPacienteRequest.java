package com.alura.clinica.dto.paciente;

import lombok.Getter;

@Getter
public class AtualizacaoPacienteRequest {
    private String nome;

    private String telefone;

    private String endereco;
}