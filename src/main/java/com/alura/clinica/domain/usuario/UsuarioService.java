package com.alura.clinica.domain.usuario;

import com.alura.clinica.domain.usuario.dto.RegisterRequest;
import com.alura.clinica.domain.usuario.dto.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public RegisterResponse registrar(RegisterRequest request) {
        var senha = request.getSenha();
        var senhaCriptografada = passwordEncoder.encode(senha);

        var usuario = new Usuario(request.getEmail(), senhaCriptografada);

        var usuarioCadastrado = usuarioRepository.save(usuario);

        return new RegisterResponse(usuarioCadastrado);
    }
}