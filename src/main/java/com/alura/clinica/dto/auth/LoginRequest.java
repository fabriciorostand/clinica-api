package com.alura.clinica.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequest {
    @NotBlank(message = "{email.obrigatorio}")
    @Email(message = "{email.login.invalido}")
    private String email;

    @NotBlank(message = "{senha.obrigatoria}")
    private String senha;
}