package com.alura.clinica.dto.medico;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class CadastroMedicoRequest {
    @NotBlank
    private String nome;

    @Email @NotBlank
    private String email;

    @NotBlank
    private String telefone;

    @Pattern(regexp = "\\d{4,6}") @NotBlank
    private String crm;

    @NotBlank
    private String especialidade;

    @NotBlank
    private String endereco;
}