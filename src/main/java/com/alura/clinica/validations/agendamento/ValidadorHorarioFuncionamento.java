package com.alura.clinica.validations.agendamento;

import com.alura.clinica.dto.consulta.AgendaConsultaRequest;
import com.alura.clinica.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Component
public class ValidadorHorarioFuncionamento implements ValidadorAgendamentoConsulta {
    public void validar(AgendaConsultaRequest request) {
        var dataConsulta = request.getData();
        var horario = request.getData().toLocalTime();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDoHorarioDeFuncionamento = horario.isBefore(LocalTime.of(7, 0));
        var depoisDoHorarioDeFuncionamento = horario.isAfter(LocalTime.of(18, 0));

        if (domingo || antesDoHorarioDeFuncionamento || depoisDoHorarioDeFuncionamento) {
            throw new ValidacaoException("Consulta não pode ser agendada no domingo ou fora do horário de funcionamento");
        }
    }
}