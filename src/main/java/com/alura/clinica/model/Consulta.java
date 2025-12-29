package com.alura.clinica.model;

import com.alura.clinica.dto.consulta.AgendaConsultaRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private LocalDateTime data;

    @Column(name = "motivo_cancelamento")
    @Enumerated(EnumType.STRING)
    private MotivoCancelamento motivoCancelamento;

    public void atualizarDados(AgendaConsultaRequest request) {
        if (request.getPacienteId() != null) {
//            this.paciente.getId() = request.getPacienteId();
        }

        if (request.getMedicoId() != null) {
//            this.medico.getId() = request.getMedicoId();
        }

        if (request.getData() != null) {
            this.data = request.getData();
        }
    }

    public void cancelar(MotivoCancelamento motivo) {
        this.motivoCancelamento = motivo;
    }
}