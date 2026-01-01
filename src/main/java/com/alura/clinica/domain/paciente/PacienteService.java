package com.alura.clinica.domain.paciente;

import com.alura.clinica.domain.paciente.dto.AtualizacaoPacienteRequest;
import com.alura.clinica.domain.paciente.dto.CadastroPacienteRequest;
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

    public Paciente buscarPorId(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Page<Paciente> listar(Pageable paginacao) {
        return pacienteRepository.findAllByAtivoTrue(paginacao);
    }

    @Transactional
    public Paciente atualizar(Long id, AtualizacaoPacienteRequest request) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        paciente.atualizarDados(request);

        return paciente;
    }

    @Transactional
    public void deletar(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        paciente.excluir();
    }
}