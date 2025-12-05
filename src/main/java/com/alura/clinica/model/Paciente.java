package com.alura.clinica.model;

import com.alura.clinica.dto.paciente.AtualizacaoPacienteRequest;
import com.alura.clinica.dto.paciente.CadastroPacienteRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pacientes")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String telefone;

    private String cpf;

    private String endereco;

    private Boolean ativo;

    // Construtor que inicializa um Paciente a partir de um PacienteRequest
    public Paciente(CadastroPacienteRequest request) {
        this.nome = request.getNome();
        this.email = request.getEmail();
        this.telefone = request.getTelefone();
        this.cpf = request.getCpf();
        this.endereco = request.getEndereco();
        this.ativo = true;
    }

    public void atualizarDados(AtualizacaoPacienteRequest request) {
        if (request.getNome() != null) {
            this.nome = request.getNome();
        }

        if (request.getTelefone() != null) {
            this.telefone = request.getTelefone();
        }

        if (request.getEndereco() != null) {
            this.endereco = request.getEndereco();
        }
    }

    // Metodo para exclusão lógica de um paciente
    public void excluir() {
        this.ativo = false;
    }
}
