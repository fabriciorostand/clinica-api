package com.alura.clinica.controller;

import com.alura.clinica.domain.paciente.dto.AtualizacaoPacienteRequest;
import com.alura.clinica.domain.paciente.dto.CadastroPacienteRequest;
import com.alura.clinica.domain.paciente.dto.ListagemPacienteResponse;
import com.alura.clinica.domain.paciente.dto.PacienteResponse;
import com.alura.clinica.domain.paciente.Paciente;
import com.alura.clinica.domain.paciente.PacienteService;
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
@RequestMapping("/api/pacientes")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class PacienteController {
    // Atributos
    private final PacienteService pacienteService;

    // Métodos
    @PostMapping
    public ResponseEntity<PacienteResponse> cadastrar(@RequestBody @Valid CadastroPacienteRequest request, UriComponentsBuilder uriBuilder) {
        var paciente = pacienteService.cadastrar(request);

        var uri = uriBuilder.path("/api/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity
                .created(uri)
                .body(paciente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> buscarPorId(@PathVariable Long id) {
        var paciente = pacienteService.buscarPorId(id);

        return ResponseEntity.ok(paciente);
    }

    @GetMapping
    public ResponseEntity<Page<ListagemPacienteResponse>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var pacientes = pacienteService.listar(paginacao);

        return ResponseEntity.ok(pacientes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponse> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoPacienteRequest request) {
        var paciente = pacienteService.atualizar(id, request);

        return ResponseEntity.ok(paciente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pacienteService.deletar(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}