package com.alura.clinica.model;

import com.alura.clinica.dto.medico.AtualizacaoMedicoRequest;
import com.alura.clinica.dto.medico.CadastroMedicoRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Medico")
@Table(name = "medicos")
@NoArgsConstructor @AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    private String especialidade;
    private String endereco;
    private Boolean ativo;
    
    // Construtor que inicializa um Medico a partir de um MedicoRequest
    public Medico(CadastroMedicoRequest request) {
        this.nome = request.getNome();
        this.email = request.getEmail();
        this.telefone = request.getTelefone();
        this.crm = request.getCrm();
        this.especialidade = request.getEspecialidade();
        this.endereco = request.getEndereco();
        this.ativo = true;
    }

    public void atualizarDados(AtualizacaoMedicoRequest request) {
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

    // Metodo para exclusão lógica de um médico
    public void excluir() {
        this.ativo = false;
    }
}