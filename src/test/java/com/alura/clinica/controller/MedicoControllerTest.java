package com.alura.clinica.controller;

import com.alura.clinica.domain.medico.Medico;
import com.alura.clinica.domain.medico.MedicoService;
import com.alura.clinica.domain.medico.dto.CadastroMedicoRequest;
import com.alura.clinica.domain.medico.dto.MedicoResponse;
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
class MedicoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<CadastroMedicoRequest> cadastroMedicoRequestJson;

    @Autowired
    private JacksonTester<MedicoResponse> medicoResponseJson;

    @MockitoBean
    private MedicoService medicoService;

    @Test
    @DisplayName("Deveria devolver código http 400 quando informações estão inválidas")
    @WithMockUser
    void cadastrarCenario1() throws Exception {
        var response = mvc.perform(post("/api/medicos"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver código http 201 quando informações estão válidas")
    @WithMockUser
    void cadastrarCenario2() throws Exception {
        var medico = new Medico(
                1L,
                "Fabricio",
                "fabricio@email.com",
                "61988888888",
                "123456", "Pediatra",
                "Brasília-DF",
                true
        );

        var medicoResponse = new MedicoResponse(medico);

        when(medicoService.cadastrar(any())).thenReturn(medicoResponse);

        var cadastroRequest = new CadastroMedicoRequest(
                "Fabricio",
                "fabricio@email.com",
                "61988888888",
                "123456",
                "Pediatra",
                "Brasília-DF"
        );

        var response = mvc.perform(post("/api/medicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cadastroMedicoRequestJson.write(
                                cadastroRequest
                        ).getJson())
                )
                .andReturn().getResponse();

        var jsonEsperado = medicoResponseJson.write(
                medicoResponse
        ).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}