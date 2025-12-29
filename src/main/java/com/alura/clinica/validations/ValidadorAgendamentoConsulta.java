package com.alura.clinica.validations;

import com.alura.clinica.dto.consulta.AgendaConsultaRequest;

public interface ValidadorAgendamentoConsulta {
    void validar(AgendaConsultaRequest request);
}