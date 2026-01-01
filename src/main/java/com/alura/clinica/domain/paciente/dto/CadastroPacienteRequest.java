package com.alura.clinica.domain.paciente.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CadastroPacienteRequest {
    @NotBlank(message = "{nome.obrigatorio}")
    private String nome;

    @NotBlank(message = "{email.obrigatorio}")
    @Email(message = "{email.invalido}")
    private String email;

    @NotBlank(message = "{telefone.obrigatorio}")
    private String telefone;

    @NotBlank(message = "{cpf.obrigatorio}")
    @Pattern(regexp = "\\d{11}", message = "{cpf.invalido}")
    private String cpf;

    @NotBlank(message = "{endereco.obrigatorio}")
    private String endereco;
}