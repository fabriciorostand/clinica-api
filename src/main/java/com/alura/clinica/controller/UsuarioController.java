package com.alura.clinica.controller;

import com.alura.clinica.model.Usuario;
import com.alura.clinica.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

//    @PostMapping
//    public ResponseEntity<Usuario> cadastrar(@RequestBody String email, String senha, UriComponentsBuilder uriBuilder) {
//        Usuario usuario = usuarioService.cadastrar(email, senha);
//
//        var uri = uriBuilder.path("/api/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
//
//        return ResponseEntity
//                .created(uri)
//                .body(usuario);
//    }
}
