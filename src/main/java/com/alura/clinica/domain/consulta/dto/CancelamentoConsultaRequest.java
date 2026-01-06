package com.alura.clinica.domain.consulta.dto;

import com.alura.clinica.domain.consulta.MotivoCancelamento;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CancelamentoConsultaRequest {
    @NotNull
    private MotivoCancelamento motivo;
}