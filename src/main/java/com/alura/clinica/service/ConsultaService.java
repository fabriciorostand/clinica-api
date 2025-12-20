package com.alura.clinica.service;

import com.alura.clinica.dto.consulta.AgendaConsultaRequest;
import com.alura.clinica.model.Consulta;
import com.alura.clinica.repository.ConsultaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ConsultaService {
    private final ConsultaRepository consultaRepository;

    @Transactional
    public Consulta agendar(AgendaConsultaRequest request) {
        Consulta consulta = new Consulta(request);

        return consultaRepository.save(consulta);
    }

    public Consulta buscarPorId(Long id) {
        return consultaRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}