package com.alura.clinica.dto.consulta;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AgendaConsultaRequest {
    @NotBlank
    private String paciente;

    @NotBlank
    private String medico;

    @NotNull
    @FutureOrPresent
    private String data;

    @NotBlank
    private String status;
}