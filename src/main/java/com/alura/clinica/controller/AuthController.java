package com.alura.clinica.controller;

import com.alura.clinica.domain.usuario.UsuarioService;
import com.alura.clinica.domain.usuario.dto.LoginRequest;
import com.alura.clinica.domain.usuario.Usuario;
import com.alura.clinica.domain.usuario.dto.RegisterRequest;
import com.alura.clinica.domain.usuario.dto.RegisterResponse;
import com.alura.clinica.infra.security.TokenJWTResponse;
import com.alura.clinica.infra.security.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    private final UsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registrar(@RequestBody @Valid RegisterRequest request) {
        var usuario = usuarioService.registrar(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenJWTResponse> logar(@RequestBody @Valid LoginRequest request) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        TokenJWTResponse response = new TokenJWTResponse(tokenJWT);

        return ResponseEntity.ok(response);
    }
}