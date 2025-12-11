package com.alura.clinica.security;

import lombok.Getter;

@Getter
public class TokenJWTResponse {
    String token;

    // Construtor que converte a String do token para TokenJWTResponse
    public TokenJWTResponse(String tokenJWT) {
        this.token = tokenJWT;
    }
}