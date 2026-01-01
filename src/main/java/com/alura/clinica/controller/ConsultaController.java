package com.alura.clinica.controller;

import com.alura.clinica.domain.consulta.dto.AgendaConsultaRequest;
import com.alura.clinica.domain.consulta.dto.CancelamentoConsultaRequest;
import com.alura.clinica.domain.consulta.dto.ConsultaResponse;
import com.alura.clinica.domain.consulta.Consulta;
import com.alura.clinica.domain.consulta.ConsultaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/consultas")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {
    private final ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<ConsultaResponse> agendar(@RequestBody @Valid AgendaConsultaRequest request, UriComponentsBuilder uriBuilder) {
        Consulta consulta = consultaService.agendar(request);

        ConsultaResponse response = new ConsultaResponse(consulta);

        var uri = uriBuilder.path("/api/consultas/{id}").buildAndExpand(consulta.getId()).toUri();

        return ResponseEntity
                .created(uri)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaResponse> buscarPorId(@PathVariable Long id) {
        Consulta consulta = consultaService.buscarPorId(id);

        ConsultaResponse response = new ConsultaResponse(consulta);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<ConsultaResponse>> listar(@PageableDefault(size = 10, sort = {"data"}, direction = Sort.Direction.DESC) Pageable paginacao) {
        Page<Consulta> consultas = consultaService.listar(paginacao);

        Page<ConsultaResponse> response = consultas.map(ConsultaResponse::new);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaResponse> atualizar(@PathVariable Long id, AgendaConsultaRequest request) {
        Consulta consulta = consultaService.atualizar(id, request);

        ConsultaResponse response = new ConsultaResponse(consulta);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping()
    public ResponseEntity<Void> cancelar(@RequestBody @Valid CancelamentoConsultaRequest request) {
        consultaService.cancelar(request);

        return ResponseEntity
                .noContent()
                .build();
    }
}