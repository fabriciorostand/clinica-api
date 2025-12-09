package com.alura.clinica.service;

import com.alura.clinica.model.Usuario;
import com.alura.clinica.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

//    public Usuario cadastrar(String email, String senha) {
//        Usuario usuario = new Usuario(email, senha);
//
//        return usuarioRepository.save(usuario);
//    }
}