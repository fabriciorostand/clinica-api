package com.alura.clinica.domain.medico.dto;

import com.alura.clinica.domain.medico.Medico;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"id", "nome", "email", "telefone", "crm", "especialidade", "endereco"})
public class MedicoResponse {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    private String especialidade;
    private String endereco;

    public MedicoResponse(Medico medico) {
        this.id = medico.getId();
        this.nome = medico.getNome();
        this.email = medico.getEmail();
        this.telefone = medico.getTelefone();
        this.crm = medico.getCrm();
        this.especialidade = medico.getEspecialidade();
        this.endereco = medico.getEndereco();
    }
}
