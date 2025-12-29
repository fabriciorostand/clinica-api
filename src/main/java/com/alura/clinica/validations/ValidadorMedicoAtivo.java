package com.alura.clinica.validations;

import com.alura.clinica.dto.consulta.AgendaConsultaRequest;
import com.alura.clinica.repository.MedicoRepository;
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
            throw new IllegalStateException("Consulta não pode ser agendada com um médico inativo");
        }
    }
}
