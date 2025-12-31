package com.alura.clinica.validations.cancelamento;

import com.alura.clinica.dto.consulta.CancelamentoConsultaRequest;

public interface ValidadorCancelamentoConsulta {
    void validar(CancelamentoConsultaRequest request);
}