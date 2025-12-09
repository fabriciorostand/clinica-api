package com.alura.clinica.controller;

import com.alura.clinica.dto.auth.LoginRequest;
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

    @PostMapping("/login")
    public ResponseEntity logar(@RequestBody @Valid LoginRequest request) {
        var token = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha());

        var authentication = manager.authenticate(token);

        return ResponseEntity.ok().build();
    }
}