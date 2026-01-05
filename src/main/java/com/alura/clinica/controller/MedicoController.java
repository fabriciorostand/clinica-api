package com.alura.clinica.controller;

import com.alura.clinica.domain.medico.dto.AtualizacaoMedicoRequest;
import com.alura.clinica.domain.medico.dto.CadastroMedicoRequest;
import com.alura.clinica.domain.medico.dto.ListagemMedicoResponse;
import com.alura.clinica.domain.medico.dto.MedicoResponse;
import com.alura.clinica.domain.medico.Medico;
import com.alura.clinica.domain.medico.MedicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "bearer-key")
public class MedicoController {
    private final MedicoService medicoService;

    // Métodos
    @PostMapping
    public ResponseEntity<MedicoResponse> cadastrar(@RequestBody @Valid CadastroMedicoRequest request, UriComponentsBuilder uriBuilder) {
        var medico = medicoService.cadastrar(request);

        var uri = uriBuilder.path("/api/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity
                .created(uri)
                .body(medico);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponse> buscarPorId(@PathVariable Long id) {
        var medico = medicoService.buscarPorId(id);

        return ResponseEntity.ok(medico);
    }

    @GetMapping
    public ResponseEntity<Page<ListagemMedicoResponse>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var medicos = medicoService.listar(paginacao);

        return ResponseEntity.ok(medicos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoResponse> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoMedicoRequest request) {
        var medico = medicoService.atualizar(id, request);

        return ResponseEntity.ok(medico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        medicoService.deletar(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}