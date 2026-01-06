package com.alura.clinica.domain.consulta;

import com.alura.clinica.domain.consulta.dto.AgendaConsultaRequest;
import com.alura.clinica.domain.consulta.dto.CancelamentoConsultaRequest;
import com.alura.clinica.domain.consulta.dto.ConsultaResponse;
import com.alura.clinica.domain.consulta.validations.ValidacaoException;
import com.alura.clinica.domain.medico.Medico;
import com.alura.clinica.domain.medico.MedicoRepository;
import com.alura.clinica.domain.paciente.PacienteRepository;
import com.alura.clinica.domain.consulta.validations.agendamento.ValidadorAgendamentoConsulta;
import com.alura.clinica.domain.consulta.validations.cancelamento.ValidadorCancelamentoConsulta;
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
    public ConsultaResponse agendar(AgendaConsultaRequest request) {
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

        return new ConsultaResponse(consultaRepository.save(consulta));
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

    public ConsultaResponse buscarPorId(Long id) {
        var consulta = consultaRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return new ConsultaResponse(consulta);
     }

    public Page<ConsultaResponse> listar(Pageable paginacao) {
        return consultaRepository
                .findAll(paginacao)
                .map(ConsultaResponse::new);
    }

    @Transactional
    public ConsultaResponse atualizar(Long id, AgendaConsultaRequest request) {
        var consulta = consultaRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        consulta.atualizarDados(request);

        return new ConsultaResponse(consulta);
    }

    @Transactional
    public void cancelar(Long id, CancelamentoConsultaRequest request) {
        if (!consultaRepository.existsById(id)) {
            throw new ValidacaoException("Id da consulta informado não existe");
        }

        validadoresCancelamento.forEach(v -> v.validar(id));

        var consulta = consultaRepository.getReferenceById(id);

        consulta.cancelar(request.getMotivo());
    }
}