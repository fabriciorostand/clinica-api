package com.alura.clinica.model;

import com.alura.clinica.dto.consulta.AgendaConsultaRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Consulta")
@Table(name = "consultas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paciente;

    private String medico;

    private String data;

    private String status;

    // Construtor que inicializa uma Consulta a partir de um AgendaConsultaRequest
    public Consulta(AgendaConsultaRequest request) {
        this.paciente = request.getPaciente();
        this.medico = request.getMedico();
        this.data = request.getData();
        this.status = request.getStatus();
    }
}