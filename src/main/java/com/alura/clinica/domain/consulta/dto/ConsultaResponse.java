package com.alura.clinica.domain.consulta.dto;

import com.alura.clinica.domain.consulta.Consulta;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonPropertyOrder({"id", "medicoId", "pacienteId", "data"})
public class ConsultaResponse {
    private final Long id;

    private final Long medicoId;

    private final Long pacienteId;

    private final LocalDateTime data;

    // Construtor que converte Consulta para ConsultaResponse
    public ConsultaResponse(Consulta consulta) {
        this.id = consulta.getId();
        this.medicoId = consulta.getMedico().getId();
        this.pacienteId = consulta.getPaciente().getId();
        this.data = consulta.getData();
    }
}