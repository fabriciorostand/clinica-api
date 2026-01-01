package com.alura.clinica.domain.consulta.validations.agendamento;

import com.alura.clinica.domain.consulta.dto.AgendaConsultaRequest;
import com.alura.clinica.domain.consulta.validations.ValidacaoException;
import com.alura.clinica.domain.consulta.ConsultaRepository;
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
            throw new ValidacaoException("Paciente já possui outra consulta agendada no mesmo dia");
        }
    }
}
