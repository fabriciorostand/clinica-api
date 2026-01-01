package com.alura.clinica.domain.consulta.validations.agendamento;

import com.alura.clinica.domain.consulta.dto.AgendaConsultaRequest;

public interface ValidadorAgendamentoConsulta {
    void validar(AgendaConsultaRequest request);
}