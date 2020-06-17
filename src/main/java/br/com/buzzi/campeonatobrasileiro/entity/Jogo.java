package br.com.buzzi.campeonatobrasileiro.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime data;
    private Integer golsTime1;
    private Integer golsTime2;
    private Integer publicoPagante;
    private Boolean encerrado;
    private Integer rodada;

    @ManyToOne
    @JoinColumn(name = "time1", insertable = false, updatable = false)
    private Time time1;

    @ManyToOne
    @JoinColumn(name = "time2", insertable = false, updatable = false)
    private Time time2;

    @Override
    public String toString() {
        return data.toString() + " - Rodada " + rodada + " - " + time1.getNome() + " " + golsTime1 + " X " + golsTime1 + " " + time2.getNome();
    }


}
