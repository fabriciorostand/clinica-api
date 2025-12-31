package com.alura.clinica.validations.agendamento;

import com.alura.clinica.dto.consulta.AgendaConsultaRequest;
import com.alura.clinica.exception.ValidacaoException;
import com.alura.clinica.repository.ConsultaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidadorMedicoComOutraConsulta implements ValidadorAgendamentoConsulta {
    private final ConsultaRepository consultaRepository;

    public void validar(AgendaConsultaRequest request) {
        var medicoId = request.getMedicoId();
        var consultaData = request.getData();

        var medicoPossuiOutraConsultaNoMesmoHorario = consultaRepository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(medicoId, consultaData);

        if (medicoPossuiOutraConsultaNoMesmoHorario) {
            throw new ValidacaoException("Medico já possui outra consulta no mesmo horario");
        }
    }
}
