package com.alura.clinica.controller;

import com.alura.clinica.dto.paciente.AtualizacaoPacienteRequest;
import com.alura.clinica.dto.paciente.CadastroPacienteRequest;
import com.alura.clinica.dto.paciente.PacienteResponse;
import com.alura.clinica.model.Paciente;
import com.alura.clinica.service.PacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/pacientes")
@RequiredArgsConstructor
public class PacienteController {
    // Atributos
    private final PacienteService pacienteService;

    // Métodos
    @PostMapping
    public ResponseEntity<Paciente> cadastrar(@RequestBody @Valid CadastroPacienteRequest request, UriComponentsBuilder uriBuilder) {
        Paciente paciente = pacienteService.cadastrar(request);

        var uri = uriBuilder.path("/api/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity
                .created(uri)
                .body(paciente);
    }

    @GetMapping
    public ResponseEntity<Page<PacienteResponse>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        Page<Paciente> lista = pacienteService.listar(paginacao);

        Page<PacienteResponse> response = lista.map(PacienteResponse::new);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponse> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoPacienteRequest request) {
        Paciente paciente = pacienteService.atualizar(id, request);

        PacienteResponse response = new PacienteResponse(paciente);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pacienteService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}