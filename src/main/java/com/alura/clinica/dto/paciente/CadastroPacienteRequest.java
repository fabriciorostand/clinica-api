package com.alura.clinica.dto.paciente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class CadastroPacienteRequest {
    @NotBlank
    private String nome;

    @Email @NotBlank
    private String email;

    @NotBlank
    private String telefone;

    @Pattern(regexp = "\\d{11}") @NotBlank
    private String cpf;

    @NotBlank
    private String endereco;
}