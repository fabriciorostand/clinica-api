package com.alura.clinica.domain.usuario.dto;

import com.alura.clinica.domain.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"id", "email"})
public class RegisterResponse {
    private final Long id;
    private final String email;

    // Construtor que converte Usuario para RegisterResponse
    public RegisterResponse(Usuario usuario) {
        this.id = usuario.getId();
        this.email = usuario.getEmail();
    }
}