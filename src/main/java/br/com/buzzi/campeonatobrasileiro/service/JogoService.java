package br.com.buzzi.campeonatobrasileiro.service;

import br.com.buzzi.campeonatobrasileiro.entity.Jogo;
import br.com.buzzi.campeonatobrasileiro.entity.Time;
import br.com.buzzi.campeonatobrasileiro.repository.JogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class JogoService {

    @Autowired
    private JogoRepository jogoRepository;

    @Autowired
    private TimeService timeService;

    /**
     * @param primeiraRodada Data da primeira rodada
     * @param datasInvalidas Datas que não podem ter jogos (ex: Datas fifa)
     */
    public void gerarJogos(LocalDateTime primeiraRodada, List<LocalDate> datasInvalidas) {
        final List<Time> times = timeService.findAll();
        List<Time> all1 = new ArrayList<>();
        List<Time> all2 = new ArrayList<>();
        all1.addAll(times);//.subList(0, times.size()/2));
        all2.addAll(times);//.subList(all1.size(), times.size()));

        jogoRepository.deleteAll();

        List<Jogo> jogos = new ArrayList<>();

        int t = times.size();
        int m = times.size() / 2;
        LocalDateTime dataJogo = primeiraRodada;
        Integer rodada = 1;
        for (int i = 0; i < t - 1; i++) {
            rodada = i + 1;
            for (int j = 0; j < m; j++) {
                //Teste para ajustar o mando de campo
                Time time1;
                Time time2;
                if (j % 2 == 1 || i % 2 == 1 && j == 0) {
                    time1 = times.get(t - j - 1);
                    time2 = times.get(j);
                } else {
                    time1 = times.get(j);
                    time2 = times.get(t - j - 1);
                }
                jogos.add(Jogo.builder().encerrado(false).golsTime1(0).golsTime2(0).rodada(rodada).publicoPagante(0).time1(time1).time2(time2).data(dataJogo).build());
                dataJogo = dataJogo.plusDays(7);
            }
            //Gira os times no sentido horário, mantendo o primeiro no lugar
            times.add(1, times.remove(times.size() - 1));
        }

        jogos.forEach(jogo -> System.out.println(jogo));

        jogoRepository.saveAll(jogos);

        LocalDateTime ultimaRodada = jogos.get(jogos.size() - 1).getData();
        List<Jogo> jogos2 = new ArrayList<>();

        jogos.forEach(jogo -> {
            Time time1 = jogo.getTime2();
            Time time2 = jogo.getTime1();
            Jogo novoJogo = new Jogo();
            novoJogo.setTime1(time1);
            novoJogo.setTime2(time2);
            novoJogo.setGolsTime2(0);
            novoJogo.setGolsTime1(0);
            novoJogo.setData(jogo.getData().plusDays(7 * jogos.size()));
            novoJogo.setRodada(jogo.getRodada() + jogos.size());
            jogos2.add(novoJogo);
        });
        jogos2.forEach(jogo -> System.out.println(jogo));
        jogoRepository.saveAll(jogos2);
    }

}
