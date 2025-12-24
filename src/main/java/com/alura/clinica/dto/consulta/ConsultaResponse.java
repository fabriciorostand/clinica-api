package com.alura.clinica.dto.consulta;

import com.alura.clinica.model.Consulta;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ConsultaResponse {
    private final Long id;

    private final Long medicoId;

    private final Long pacienteId;

    private final LocalDateTime data;

    // Construtor que converte Consulta para ConsultaResponse
    public ConsultaResponse(Consulta consulta) {
        this.id = consulta.getId();
        this.pacienteId = consulta.getId();
        this.medicoId = consulta.getId();
        this.data = consulta.getData();
    }
}
