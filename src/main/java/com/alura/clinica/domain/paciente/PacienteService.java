package com.alura.clinica.domain.paciente;

import com.alura.clinica.domain.paciente.dto.AtualizacaoPacienteRequest;
import com.alura.clinica.domain.paciente.dto.CadastroPacienteRequest;
import com.alura.clinica.domain.paciente.dto.ListagemPacienteResponse;
import com.alura.clinica.domain.paciente.dto.PacienteResponse;
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
    public PacienteResponse cadastrar(CadastroPacienteRequest request) {
        var paciente = new Paciente(request);

        return new PacienteResponse(pacienteRepository.save(paciente));
    }

    public PacienteResponse buscarPorId(Long id) {
        var paciente = pacienteRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return new PacienteResponse(paciente);
    }

    public Page<ListagemPacienteResponse> listar(Pageable paginacao) {
        return pacienteRepository
                .findAllByAtivoTrue(paginacao)
                .map(ListagemPacienteResponse::new);
    }

    @Transactional
    public PacienteResponse atualizar(Long id, AtualizacaoPacienteRequest request) {
        var paciente = pacienteRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        paciente.atualizarDados(request);

        return new PacienteResponse(paciente);
    }

    @Transactional
    public void deletar(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        paciente.excluir();
    }
}