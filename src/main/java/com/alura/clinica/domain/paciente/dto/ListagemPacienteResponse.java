package com.alura.clinica.domain.paciente.dto;

import com.alura.clinica.domain.paciente.Paciente;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
// Define a ordem das propriedades na serialização JSON
@JsonPropertyOrder({"id", "nome", "email", "telefone"})
public class ListagemPacienteResponse {
    private Long id;
    private String nome;
    private String email;
    private String cpf;

    // Construtor que converte Paciente para PacienteResponse
    public ListagemPacienteResponse(Paciente paciente) {
        this.id = paciente.getId();
        this.nome = paciente.getNome();
        this.email = paciente.getEmail();
        this.cpf = paciente.getCpf();
    }
}