package br.com.buzzi.campeonatobrasileiro.repository;

import br.com.buzzi.campeonatobrasileiro.entity.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeRepository extends JpaRepository<Time, Integer> {

    List<Time> findByNomeIgnoreCaseAndAndIdNot(String nome, Integer id);

}
