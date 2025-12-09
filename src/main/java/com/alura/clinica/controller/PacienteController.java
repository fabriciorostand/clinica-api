package com.alura.clinica.controller;

import com.alura.clinica.dto.paciente.AtualizacaoPacienteRequest;
import com.alura.clinica.dto.paciente.CadastroPacienteRequest;
import com.alura.clinica.dto.paciente.ListagemPacienteResponse;
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
    public ResponseEntity<PacienteResponse> cadastrar(@RequestBody @Valid CadastroPacienteRequest request, UriComponentsBuilder uriBuilder) {
        Paciente paciente = pacienteService.cadastrar(request);

        var uri = uriBuilder.path("/api/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

        PacienteResponse response = new PacienteResponse(paciente);

        return ResponseEntity
                .created(uri)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> buscarPorId(@PathVariable Long id) {
        Paciente paciente = pacienteService.buscarPorId(id);

        PacienteResponse response = new PacienteResponse(paciente);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<ListagemPacienteResponse>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        Page<Paciente> lista = pacienteService.listar(paginacao);

        Page<ListagemPacienteResponse> response = lista.map(ListagemPacienteResponse::new);

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