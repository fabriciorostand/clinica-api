package com.alura.clinica.validations.cancelamento;

import com.alura.clinica.dto.consulta.CancelamentoConsultaRequest;
import com.alura.clinica.exception.ValidacaoException;
import com.alura.clinica.repository.ConsultaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ValidadorAntecedenciaCancelamento implements ValidadorCancelamentoConsulta{
    private final ConsultaRepository consultaRepository;

    public void validar(CancelamentoConsultaRequest request) {
        var consulta = consultaRepository.getReferenceById(request.getConsultaId());

        var dataConsulta = consulta.getData();
        var dataAtual = LocalDateTime.now();

        var diferencaHoras = Duration.between(dataAtual, dataConsulta).toHours();

        if (diferencaHoras < 24) {
            throw new ValidacaoException("Consulta somente pode ser cancelada com antecedência mínima de 24h");
        }
    }
}