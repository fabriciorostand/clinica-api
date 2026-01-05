package com.alura.clinica.domain.usuario.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class RegisterRequest {

    @NotBlank(message = "{email.obrigatorio}")
    @Email(message = "{email.login.invalido}")
    private String email;

    @NotBlank(message = "{senha.obrigatoria}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d!@#$%^&*()_+=\\-{}|:;\"'<>,.?/]{8,}$", message = "{senha.invalida}")
    private String senha;
}
