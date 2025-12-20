package com.alura.clinica.controller;

import com.alura.clinica.dto.consulta.AgendaConsultaRequest;
import com.alura.clinica.dto.consulta.ConsultaResponse;
import com.alura.clinica.model.Consulta;
import com.alura.clinica.service.ConsultaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController("/api/consultas")
@RequiredArgsConstructor
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
    public ResponseEntity<ConsultaResponse> buscarPorID(@PathVariable Long id) {
        Consulta consulta = consultaService.buscarPorId(id);

        ConsultaResponse response = new ConsultaResponse(consulta);

        return ResponseEntity.ok(response);
    }
}