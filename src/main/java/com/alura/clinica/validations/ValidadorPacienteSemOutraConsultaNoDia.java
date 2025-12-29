package com.alura.clinica.validations;

import com.alura.clinica.dto.consulta.AgendaConsultaRequest;
import com.alura.clinica.repository.ConsultaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoConsulta {
    private final ConsultaRepository consultaRepository;

    public void validar(AgendaConsultaRequest request) {
        var pacienteId = request.getPacienteId();
        var primeiroHorario = request.getData().withHour(7);
        var ultimoHorario = request.getData().withHour(18);

        var pacientePossuiOutraConsultaNoDia = consultaRepository.existsByPacienteIdAndDataBetween(pacienteId, primeiroHorario, ultimoHorario);

        if (pacientePossuiOutraConsultaNoDia) {
            throw new IllegalStateException("Paciente já possui outra consulta agendada no mesmo dia");
        }
    }
}
