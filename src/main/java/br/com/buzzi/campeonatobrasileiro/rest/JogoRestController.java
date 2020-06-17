package br.com.buzzi.campeonatobrasileiro.rest;

import br.com.buzzi.campeonatobrasileiro.dto.NovoTimeDTO;
import br.com.buzzi.campeonatobrasileiro.dto.TimeDTO;
import br.com.buzzi.campeonatobrasileiro.service.JogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Arrays;

@RestController
@RequestMapping(path = "/jogos")
public class JogoRestController {

    @Autowired
    private JogoService jogoService;

    @PostMapping
    public ResponseEntity<Void> gerarJogos() {
        jogoService.gerarJogos(LocalDateTime.now(), Arrays.asList());
        return ResponseEntity.ok().build();
    }


}
