package br.com.buzzi.campeonatobrasileiro.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@Entity
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime data;
    private Integer golsTime1;
    private Integer golsTime2;
    private Integer publicoPagante;
    private Boolean encerrado;

    @ManyToOne
    @JoinColumn(name="time1", insertable = false, updatable = false)
    private Time time1;

    @ManyToOne
    @JoinColumn(name="time2", insertable = false, updatable = false)
    private Time time2;


}
