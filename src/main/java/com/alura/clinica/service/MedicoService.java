package com.alura.clinica.service;

import com.alura.clinica.dto.medico.AtualizacaoMedicoRequest;
import com.alura.clinica.dto.medico.CadastroMedicoRequest;
import com.alura.clinica.model.Medico;
import com.alura.clinica.repository.MedicoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MedicoService {
    // Atributos
    private final MedicoRepository medicoRepository;

    // Métodos
    @Transactional
    public Medico cadastrar(CadastroMedicoRequest request) {
        Medico medico = new Medico(request);

        return medicoRepository.save(medico);
    }

    public Page<Medico> listar(Pageable paginacao) {
        return medicoRepository.findAllByAtivoTrue(paginacao);
    }

    @Transactional
    public Medico atualizar(Long id, AtualizacaoMedicoRequest request) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Médico não encontrado"));

        medico.atualizarDados(request);

        return medico;
    }

    @Transactional
    public void deletar(Long id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

        medico.excluir();
    }
}