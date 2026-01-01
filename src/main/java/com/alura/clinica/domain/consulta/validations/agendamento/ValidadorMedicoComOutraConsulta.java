package com.alura.clinica.domain.consulta.validations.agendamento;

import com.alura.clinica.domain.consulta.dto.AgendaConsultaRequest;
import com.alura.clinica.domain.consulta.validations.ValidacaoException;
import com.alura.clinica.domain.consulta.ConsultaRepository;
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
