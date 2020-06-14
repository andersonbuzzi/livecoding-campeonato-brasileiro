package br.com.buzzi.campeonatobrasileiro.service;

import br.com.buzzi.campeonatobrasileiro.entity.Jogo;
import br.com.buzzi.campeonatobrasileiro.repository.JogoRepository;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class JogoService {

    @Autowired
    private JogoRepository jogoRepository;

    /**
     *
     * @param primeiraRodada Data da primeira rodada
     * @param jogosMeioDaSemana Se pode ter jogos no meio da semana (quarta)
     * @param jogosFinsDeSemana Se pode ter jogos fins de semana (sábado e domingo)
     * @param datasInvalidas Datas que não podem ter jogos (ex: Datas fifa)
     */
    public void gerarJogos(LocalDateTime primeiraRodada, Boolean jogosMeioDaSemana, Boolean jogosFinsDeSemana, List<LocalDate> datasInvalidas) {
        //TODO
    }

}
