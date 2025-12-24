package com.alura.clinica.service;

import com.alura.clinica.dto.consulta.AgendaConsultaRequest;
import com.alura.clinica.model.Consulta;
import com.alura.clinica.model.Medico;
import com.alura.clinica.model.Paciente;
import com.alura.clinica.repository.ConsultaRepository;
import com.alura.clinica.repository.MedicoRepository;
import com.alura.clinica.repository.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ConsultaService {
    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    @Transactional
    public Consulta agendar(AgendaConsultaRequest request) {
        Long pacienteId = request.getPacienteId();
        Long medicoId = request.getMedicoId();

        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(EntityNotFoundException::new);

        if (paciente.getAtivo() != true) {
            throw new IllegalStateException("Erro: Paciente inativo.");
        }

        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(EntityNotFoundException::new);

        if (medico.getAtivo() != true) {
            throw new IllegalStateException("Erro: Médico inativo.");
        }

        Consulta consulta = new Consulta(request);

        return consultaRepository.save(consulta);
    }

     public Consulta buscarPorId(Long id) {
        return consultaRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
     }

    public Page<Consulta> listar(Pageable paginacao) {
        return consultaRepository.findByAtivoTrue(paginacao);
    }

    public Consulta atualizar(Long id, AgendaConsultaRequest request) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        consulta.atualizarDados(request);

        return consulta;
    }

    public void excluir(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        consulta.excluir();
    }
}