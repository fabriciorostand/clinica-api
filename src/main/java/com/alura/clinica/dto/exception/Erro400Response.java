package com.alura.clinica.dto.exception;

import lombok.Getter;
import org.springframework.validation.FieldError;

@Getter
public class Erro400Response {
    private final String campo;
    private final String mensagem;

    // Construtor que converte FieldError para Erro400Response
    public Erro400Response(FieldError erro) {
        this.campo = erro.getField();
        this.mensagem = erro.getDefaultMessage();
    }
}