package br.com.buzzi.campeonatobrasileiro.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JogoDTO {
    private Integer id;
    private LocalDateTime data;
    private Integer golsTime1;
    private Integer golsTime2;
    private Integer publicoPagante;
    private Boolean encerrado;
    private Integer rodada;
    private TimeDTO time1;
    private TimeDTO time2;
}
