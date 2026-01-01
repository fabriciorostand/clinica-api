package com.alura.clinica.domain.consulta.validations.agendamento;

import com.alura.clinica.domain.consulta.dto.AgendaConsultaRequest;
import com.alura.clinica.domain.consulta.validations.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoConsulta {
    public void validar(AgendaConsultaRequest request) {
        var horarioConsulta = request.getData();
        var horarioAtual = LocalDateTime.now();

        var diferencaMinutos = Duration.between(horarioAtual, horarioConsulta).toMinutes();

        if (diferencaMinutos < 30) {
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
        }
    }
}