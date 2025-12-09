package com.alura.clinica.dto.medico;

import com.alura.clinica.model.Medico;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
// Define a ordem das propriedades na serialização JSON
@JsonPropertyOrder({"id", "nome", "email", "crm", "especialidade"})
public class ListagemMedicoResponse {
    private Long id;
    private String nome;
    private String email;
    private String crm;
    private String especialidade;

    // Construtor que converte Medico para MedicoResponse
    public ListagemMedicoResponse(Medico medico) {
        this.id = medico.getId();
        this.nome = medico.getNome();
        this.email = medico.getEmail();
        this.crm = medico.getCrm();
        this.especialidade = medico.getEspecialidade();
    }
}