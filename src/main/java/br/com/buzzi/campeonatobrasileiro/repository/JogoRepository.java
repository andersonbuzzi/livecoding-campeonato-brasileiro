package br.com.buzzi.campeonatobrasileiro.repository;

import br.com.buzzi.campeonatobrasileiro.entity.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Integer> {

}
