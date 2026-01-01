package com.alura.clinica.domain.medico;

import com.alura.clinica.domain.consulta.Consulta;
import com.alura.clinica.domain.medico.dto.CadastroMedicoRequest;
import com.alura.clinica.domain.paciente.Paciente;
import com.alura.clinica.domain.paciente.dto.CadastroPacienteRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria devolver null quando o único médico cadastrado não está disponível na data")
    void escolherMedicoAleatorioLivreNaDataCenario1() {
        // given ou arrange
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var medico = cadastrarMedico("Medico", "medico@email.com", "61981810000", "123456", "Pediatra");
        var paciente = cadastrarPaciente("Paciente", "paciente@email.com", "61981812222", "03090011111");
        cadastrarConsulta(medico, paciente, proximaSegundaAs10);

        // when ou act
        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(proximaSegundaAs10, "Pediatra");

        // then ou assert
        assertThat(medicoLivre).isNull();
    }

    @Test
    @DisplayName("Deveria devolver médico quando ele estiver disponível na data")
    void escolherMedicoAleatorioLivreNaDataCenario2() {
        // given ou arrange
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var medico = cadastrarMedico("Medico", "medico@email.com", "61981810000", "123456", "Pediatra");

        // when ou act
        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(proximaSegundaAs10, "Pediatra");

        // then ou assert
        assertThat(medicoLivre).isEqualTo(medico);
    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        em.persist(new Consulta(null, medico, paciente, data, null));
    }

    private Medico cadastrarMedico(String nome, String email, String telefone, String crm, String especialidade) {
        var medico = new Medico(dadosMedico(nome, email, telefone, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String telefone, String cpf) {
        var paciente = new Paciente(dadosPaciente(nome, email, telefone, cpf));
        em.persist(paciente);
        return paciente;
    }

    private CadastroMedicoRequest dadosMedico(String nome, String email, String telefone, String crm, String especialidade) {
        return new CadastroMedicoRequest(
                nome,
                email,
                telefone,
                crm,
                especialidade,
                "Brasília-DF"
        );
    }

    private CadastroPacienteRequest dadosPaciente(String nome, String email, String telefone, String cpf) {
        return new CadastroPacienteRequest(
                nome,
                email,
                telefone,
                cpf,
                "Brasília-DF"
        );
    }

}