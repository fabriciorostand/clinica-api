package com.alura.clinica.validations;

import com.alura.clinica.dto.consulta.AgendaConsultaRequest;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoConsulta {
    public void validar(AgendaConsultaRequest request) {
        var horarioConsulta = request.getData();
        var horarioAtual = LocalTime.now();

        var diferencaMinutos = Duration.between(horarioAtual, horarioConsulta).toMinutes();

        if (diferencaMinutos <  30) {
            throw new IllegalStateException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
        }
    }
}
