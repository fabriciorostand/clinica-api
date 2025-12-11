package com.alura.clinica.controller;

import com.alura.clinica.dto.auth.LoginRequest;
import com.alura.clinica.model.Usuario;
import com.alura.clinica.security.TokenJWTResponse;
import com.alura.clinica.security.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager manager;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<TokenJWTResponse> logar(@RequestBody @Valid LoginRequest request) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        TokenJWTResponse response = new TokenJWTResponse(tokenJWT);

        return ResponseEntity.ok(response);
    }
}