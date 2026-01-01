package com.alura.clinica.domain.consulta.validations.cancelamento;

import com.alura.clinica.domain.consulta.dto.CancelamentoConsultaRequest;

public interface ValidadorCancelamentoConsulta {
    void validar(CancelamentoConsultaRequest request);
}