package com.alura.clinica.controller;

import com.alura.clinica.dto.medico.AtualizacaoMedicoRequest;
import com.alura.clinica.dto.medico.CadastroMedicoRequest;
import com.alura.clinica.dto.medico.ListagemMedicoResponse;
import com.alura.clinica.dto.medico.MedicoResponse;
import com.alura.clinica.model.Medico;
import com.alura.clinica.service.MedicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/medicos")
@RequiredArgsConstructor
public class MedicoController {
    private final MedicoService medicoService;

    // Métodos
    @PostMapping
    public ResponseEntity<MedicoResponse> cadastrar(@RequestBody @Valid CadastroMedicoRequest request, UriComponentsBuilder uriBuilder) {
        Medico medico = medicoService.cadastrar(request);

        MedicoResponse response = new MedicoResponse(medico);

        var uri = uriBuilder.path("/api/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity
                .created(uri)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponse> buscarPorId(@PathVariable Long id) {
        Medico medico = medicoService.buscarPorId(id);

        MedicoResponse response = new MedicoResponse(medico);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<ListagemMedicoResponse>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        Page<Medico> lista = medicoService.listar(paginacao);

        Page<ListagemMedicoResponse> response = lista.map(ListagemMedicoResponse::new);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoResponse> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoMedicoRequest request) {
        Medico medico = medicoService.atualizar(id, request);

        MedicoResponse response = new MedicoResponse(medico);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        medicoService.deletar(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}