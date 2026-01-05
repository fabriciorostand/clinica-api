package com.alura.clinica.controller;

import com.alura.clinica.domain.paciente.Paciente;
import com.alura.clinica.domain.paciente.PacienteService;
import com.alura.clinica.domain.paciente.dto.CadastroPacienteRequest;
import com.alura.clinica.domain.paciente.dto.PacienteResponse;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
class PacienteControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<CadastroPacienteRequest> cadastroPacienteRequestJson;

    @Autowired
    private JacksonTester<PacienteResponse> pacienteResponseJson;

    @MockitoBean
    private PacienteService pacienteService;

    @Test
    @DisplayName("Deveria devolver código http 400 quando informações estão inválidas")
    @WithMockUser
    void cadastrarCenario1() throws Exception {
        var response = mvc.perform(post("/api/pacientes"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver código http 201 quando informações estão válidas")
    @WithMockUser
    void cadastrarCenario2() throws Exception {
        var paciente = new Paciente(
                1L,
                "Fulano",
                "fulano@email.com",
                "61977777777",
                "01090080010",
                "Brasília-DF",
                true
        );

        var pacienteResponse = new PacienteResponse(paciente);

        when(pacienteService.cadastrar(any())).thenReturn(pacienteResponse);

        var cadastroRequest = new CadastroPacienteRequest(
                "Fulano",
                "fulano@email.com",
                "61977777777",
                "01090080010",
                "Brasília-DF"
        );

        var response = mvc.perform(post("/api/pacientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cadastroPacienteRequestJson.write(
                                cadastroRequest
                        ).getJson())
                )
                .andReturn().getResponse();

        var jsonEsperado = pacienteResponseJson.write(
                pacienteResponse
        ).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}