package com.alura.clinica.service;

import com.alura.clinica.dto.paciente.AtualizacaoPacienteRequest;
import com.alura.clinica.dto.paciente.CadastroPacienteRequest;
import com.alura.clinica.model.Paciente;
import com.alura.clinica.repository.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PacienteService {
    // Atributos
    private final PacienteRepository pacienteRepository;

    // Métodos
    @Transactional
    public Paciente cadastrar(CadastroPacienteRequest request) {
        Paciente paciente = new Paciente(request);

        return pacienteRepository.save(paciente);
    }

    public Page<Paciente> listar(Pageable paginacao) {
        return pacienteRepository.findAllByAtivoTrue(paginacao);
    }

    @Transactional
    public Paciente atualizar(Long id, AtualizacaoPacienteRequest request) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));

        paciente.atualizarDados(request);

        return paciente;
    }

    @Transactional
    public void deletar(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));

        paciente.excluir();
    }
}