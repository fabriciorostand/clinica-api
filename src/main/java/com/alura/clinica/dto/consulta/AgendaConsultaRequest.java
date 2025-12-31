package com.alura.clinica.dto.consulta;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AgendaConsultaRequest {
    private Long medicoId;

    @NotNull
    private Long pacienteId;

    @NotNull
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime data;

    private String especialidade;
}