package com.alura.clinica.repository;

import com.alura.clinica.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(Long medicoId, LocalDateTime consultaData);

    boolean existsByPacienteIdAndDataBetween(Long pacienteId, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);
}