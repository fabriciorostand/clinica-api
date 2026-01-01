package com.alura.clinica.domain.paciente.dto;

import com.alura.clinica.domain.paciente.Paciente;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"id", "nome", "email", "telefone", "cpf", "endereco"})
public class PacienteResponse {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private String endereco;

    public PacienteResponse(Paciente paciente) {
        this.id = paciente.getId();
        this.nome = paciente.getNome();
        this.email = paciente.getEmail();
        this.telefone = paciente.getTelefone();
        this.cpf = paciente.getCpf();
        this.endereco = paciente.getEndereco();
    }
}
