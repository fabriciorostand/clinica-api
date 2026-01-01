package com.alura.clinica.domain.medico.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CadastroMedicoRequest {
    @NotBlank(message = "{nome.obrigatorio}")
    private String nome;

    @NotBlank(message = "{email.obrigatorio}")
    @Email(message = "{email.invalido}")
    private String email;

    @NotBlank(message = "{telefone.obrigatorio}")
    private String telefone;

    @NotBlank(message = "{crm.obrigatorio}")
    @Pattern(regexp = "\\d{4,6}", message = "{crm.invalido}")
    private String crm;

    @NotBlank(message = "{especialidade.obrigatorio}")
    private String especialidade;

    @NotBlank(message = "{endereco.obrigatorio}")
    private String endereco;
}