package com.alura.clinica.service;

import com.alura.clinica.dto.consulta.AgendaConsultaRequest;
import com.alura.clinica.dto.consulta.CancelamentoConsultaRequest;
import com.alura.clinica.exception.ValidacaoException;
import com.alura.clinica.model.Consulta;
import com.alura.clinica.model.Medico;
import com.alura.clinica.repository.ConsultaRepository;
import com.alura.clinica.repository.MedicoRepository;
import com.alura.clinica.repository.PacienteRepository;
import com.alura.clinica.validations.agendamento.ValidadorAgendamentoConsulta;
import com.alura.clinica.validations.cancelamento.ValidadorCancelamentoConsulta;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultaService {
    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;
    private final List<ValidadorAgendamentoConsulta> validadoresAgendamento;
    private final List<ValidadorCancelamentoConsulta> validadoresCancelamento;

    @Transactional
    public Consulta agendar(AgendaConsultaRequest request) {
        Long pacienteId = request.getPacienteId();
        Long medicoId = request.getMedicoId();
        LocalDateTime data = request.getData();

        if (!pacienteRepository.existsById(pacienteId)) {
            throw new ValidacaoException("Id do paciente informado não existe");
        }

        if (medicoId != null && !medicoRepository.existsById(medicoId)) {
            throw new ValidacaoException("Id do médico informado não existe");
        }

        validadoresAgendamento.forEach(v -> v.validar(request));

        var paciente = pacienteRepository.getReferenceById(pacienteId);
        var medico = escolherMedico(request);

        if (medico == null) {
            throw new ValidacaoException("Nenhum médico disponível para esse horário nesta data");
        }

        var consulta = new Consulta(null, medico, paciente, data, null);

        return consultaRepository.save(consulta);
    }

    private Medico escolherMedico(AgendaConsultaRequest request) {
        if (request.getMedicoId() != null) {
            return medicoRepository.getReferenceById(request.getMedicoId());
        }

        if (request.getEspecialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for informado.");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(request.getData(), request.getEspecialidade());
    }

    public Consulta buscarPorId(Long id) {
        return consultaRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
     }

    public Page<Consulta> listar(Pageable paginacao) {
        return consultaRepository.findAll(paginacao);
    }

    public Consulta atualizar(Long id, AgendaConsultaRequest request) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        consulta.atualizarDados(request);

        return consulta;
    }

    @Transactional
    public void cancelar(CancelamentoConsultaRequest request) {
        Long id = request.getConsultaId();

        if (!consultaRepository.existsById(id)) {
            throw new ValidacaoException("Id da consulta informado não existe");
        }

        validadoresCancelamento.forEach(v -> v.validar(request));

        var consulta = consultaRepository.getReferenceById(id);

        consulta.cancelar(request.getMotivo());
    }
}