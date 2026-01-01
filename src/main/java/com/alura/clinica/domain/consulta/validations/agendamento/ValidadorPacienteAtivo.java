package com.alura.clinica.domain.consulta.validations.agendamento;

import com.alura.clinica.domain.consulta.dto.AgendaConsultaRequest;
import com.alura.clinica.domain.consulta.validations.ValidacaoException;
import com.alura.clinica.domain.paciente.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidadorPacienteAtivo implements ValidadorAgendamentoConsulta {
    private final PacienteRepository pacienteRepository;

    public void validar(AgendaConsultaRequest request) {
        if (request.getPacienteId() == null) {
            return;
        }

        var pacienteId = request.getPacienteId();
        var pacienteEstaAtivo = pacienteRepository.findAtivoById(pacienteId);

        if (!pacienteEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada para um paciente inativo");
        }
    }
}