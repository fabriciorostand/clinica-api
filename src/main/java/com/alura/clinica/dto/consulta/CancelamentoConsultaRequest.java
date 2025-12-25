package com.alura.clinica.dto.consulta;

import com.alura.clinica.model.MotivoCancelamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CancelamentoConsultaRequest {
    @NotNull
    private Long consultaId;

    @NotBlank
    private MotivoCancelamento motivo;
}