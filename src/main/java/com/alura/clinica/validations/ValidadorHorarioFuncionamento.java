package com.alura.clinica.validations;

import com.alura.clinica.dto.consulta.AgendaConsultaRequest;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamento implements ValidadorAgendamentoConsulta {
    public void validar(AgendaConsultaRequest request) {
        var dataConsulta = request.getData();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDoHorarioDeFuncionamento = dataConsulta.getHour() < 7;
        var depoisDoHorarioDeFuncionamento = dataConsulta.getHour() > 18;

        if (domingo || antesDoHorarioDeFuncionamento || depoisDoHorarioDeFuncionamento) {
            throw new IllegalArgumentException("Consulta não pode ser agendada em domingo ou fora do horário de funcionamento");
        }
    }

}
