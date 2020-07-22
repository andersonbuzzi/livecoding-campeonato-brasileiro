package br.com.buzzi.campeonatobrasileiro.service;

import br.com.buzzi.campeonatobrasileiro.dto.NovoTimeDTO;
import br.com.buzzi.campeonatobrasileiro.dto.TimeDTO;
import br.com.buzzi.campeonatobrasileiro.entity.Time;
import br.com.buzzi.campeonatobrasileiro.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TimeService {

    @Autowired
    private TimeRepository timeRepository;

    public List<TimeDTO> getTimes() {
        return findAll().stream().map(entity -> entityToDto(entity)).collect(Collectors.toList());
    }

    public List<Time> findAll() {
        return timeRepository.findAll();
    }

    public TimeDTO entityToDto(Time entity) {
        TimeDTO timeDTO = new TimeDTO();
        timeDTO.setId(entity.getId());
        timeDTO.setNome(entity.getNome());
        timeDTO.setSigla(entity.getSigla());
        return timeDTO;
    }

    public TimeDTO adicionarTime(NovoTimeDTO novoTimeDTO) throws Exception {
        if (timeJaExiste(novoTimeDTO.getNome(), 0)) {
            throw new Exception(String.format("Time %s já existe.", novoTimeDTO.getNome()));
        }
        Time time = dtoToEntity(novoTimeDTO);
        return entityToDto(timeRepository.save(time));
    }

    public TimeDTO atualizarTime(Integer id, NovoTimeDTO novoTimeDTO) throws Exception {
        if (timeJaExiste(novoTimeDTO.getNome(), id)) {
            throw new Exception(String.format("Time %s já existe.", novoTimeDTO.getNome()));
        }
        final Optional<Time> optionalTime = timeRepository.findById(id);
        if (optionalTime.isPresent()) {
            final Time time = optionalTime.get();
            return entityToDto(timeRepository.save(dtoToEntity(novoTimeDTO, time)));
        } else {
            throw new Exception(String.format("Time com id %d inexistente", id));
        }
    }

    private Boolean timeJaExiste(String nome, Integer id) {
        return timeRepository.findByNomeIgnoreCaseAndAndIdNot(nome, id).size() > 0;
    }

    public Time dtoToEntity(NovoTimeDTO novoTimeDTO) {
        Time time = new Time();
        return dtoToEntity(novoTimeDTO, time);
    }

    private Time dtoToEntity(NovoTimeDTO novoTimeDTO, Time time) {
        time.setEstado(novoTimeDTO.getEstado());
        time.setNome(novoTimeDTO.getNome());
        time.setSigla(novoTimeDTO.getSigla());
        return time;
    }

    public TimeDTO obterTime(Integer id) throws Exception {
        final Optional<Time> optionalTime = timeRepository.findById(id);
        if (optionalTime.isPresent()) {
            final Time entity = optionalTime.get();
            return entityToDto(entity);
        } else {
            throw new Exception(String.format("Time com id %d inexistente", id));
        }
    }

    public void deletarTime(Integer id) throws Exception {
        final Optional<Time> optionalTime = timeRepository.findById(id);
        if (optionalTime.isPresent()) {
            timeRepository.delete(optionalTime.get());
        } else {
            throw new Exception(String.format("Time com id %d inexistente", id));
        }
    }
}
