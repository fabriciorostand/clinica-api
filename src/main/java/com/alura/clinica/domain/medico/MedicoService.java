package com.alura.clinica.domain.medico;

import com.alura.clinica.domain.medico.dto.AtualizacaoMedicoRequest;
import com.alura.clinica.domain.medico.dto.CadastroMedicoRequest;
import com.alura.clinica.domain.medico.dto.ListagemMedicoResponse;
import com.alura.clinica.domain.medico.dto.MedicoResponse;
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
    public MedicoResponse cadastrar(CadastroMedicoRequest request) {
        Medico medico = new Medico(request);

        return new MedicoResponse(medicoRepository.save(medico));
    }

    public MedicoResponse buscarPorId(Long id) {
        var consulta = medicoRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return new MedicoResponse(consulta);
    }

    public Page<ListagemMedicoResponse> listar(Pageable paginacao) {
        return medicoRepository
                .findAllByAtivoTrue(paginacao)
                .map(ListagemMedicoResponse::new);
    }

    @Transactional
    public MedicoResponse atualizar(Long id, AtualizacaoMedicoRequest request) {
        var medico = medicoRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        medico.atualizarDados(request);

        return new MedicoResponse(medico);
    }

    @Transactional
    public void deletar(Long id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        medico.excluir();
    }
}