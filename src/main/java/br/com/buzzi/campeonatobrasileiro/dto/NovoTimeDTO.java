package br.com.buzzi.campeonatobrasileiro.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class NovoTimeDTO {

    @NotEmpty(message = "Nome é obrigatório")
    private String nome;
    @NotEmpty(message = "Sigla é obrigatória")
    private String sigla;
    @NotEmpty(message = "Estado é obrigatório")
    private String estado;

}
