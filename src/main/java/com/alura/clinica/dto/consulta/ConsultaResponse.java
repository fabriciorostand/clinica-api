package com.alura.clinica.dto.consulta;

import com.alura.clinica.model.Consulta;
import lombok.Getter;

@Getter
public class ConsultaResponse {
    private final Long id;

    private final String paciente;

    private final String medico;

    private final String data;

    // Construtor que converte Consulta para ConsultaResponse
    public ConsultaResponse(Consulta consulta) {
        this.id = consulta.getId();
        this.paciente = consulta.getPaciente();
        this.medico = consulta.getMedico();
        this.data = consulta.getData();
    }
}
