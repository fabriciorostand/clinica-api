package com.alura.clinica.controller;

import com.alura.clinica.domain.consulta.Consulta;
import com.alura.clinica.domain.consulta.ConsultaService;
import com.alura.clinica.domain.consulta.dto.AgendaConsultaRequest;
import com.alura.clinica.domain.consulta.dto.ConsultaResponse;
import com.alura.clinica.domain.medico.Medico;
import com.alura.clinica.domain.paciente.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<AgendaConsultaRequest> agendaConsultaRequestJson;

    @Autowired
    private JacksonTester<ConsultaResponse> consultaResponseJson;

    @MockitoBean
    private ConsultaService consultaService;

    @Test
    @DisplayName("Deveria devolver código http 400 quando informações estão inválidas")
    @WithMockUser
    void agendarCenario1() throws Exception {
        var response = mvc.perform(post("/api/consultas"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver código http 201 quando informações estão válidas")
    @WithMockUser
    void agendarCenario2() throws Exception {
        var data = LocalDateTime.now().plusHours(1);

        var consulta = new Consulta(
                null,
                new Medico(1L, "Fabricio", "fabricio@email.com", "61988888888", "123456", "Pediatra", "Brasília-DF", true),
                new Paciente(1L, "Fulano", "fulano@email.com", "61977777777", "01090080010", "Brasília-DF", true),
                data,
                null
        );

        var consultaResponse = new ConsultaResponse(consulta);

        when(consultaService.agendar(any())).thenReturn(consultaResponse);

        var agendamentoRequest = new AgendaConsultaRequest(
                1L,
                1L,
                data,
                "Pediatra"
        );

        var response = mvc.perform(post("/api/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(agendaConsultaRequestJson.write(
                                agendamentoRequest
                        ).getJson())
                )
                .andReturn().getResponse();

        var jsonEsperado = consultaResponseJson.write(
                consultaResponse
        ).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}