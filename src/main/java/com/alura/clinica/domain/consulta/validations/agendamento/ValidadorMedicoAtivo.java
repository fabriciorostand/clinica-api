package com.alura.clinica.domain.consulta.validations.agendamento;

import com.alura.clinica.domain.consulta.dto.AgendaConsultaRequest;
import com.alura.clinica.domain.consulta.validations.ValidacaoException;
import com.alura.clinica.domain.medico.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidadorMedicoAtivo implements ValidadorAgendamentoConsulta {
    private final MedicoRepository medicoRepository;

    public void validar(AgendaConsultaRequest request) {
        if (request.getMedicoId() == null) {
            return;
        }

        var medicoId = request.getMedicoId();
        var medicoEstaAtivo = medicoRepository.findAtivoById(medicoId);

        if (!medicoEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com um médico inativo");
        }
    }
}
