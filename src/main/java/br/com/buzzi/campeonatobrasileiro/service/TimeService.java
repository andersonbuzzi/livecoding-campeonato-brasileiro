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
        return timeRepository.findAll().stream().map(entity -> entityToDto(entity)).collect(Collectors.toList());
    }

    private TimeDTO entityToDto(Time entity) {
        TimeDTO timeDTO = new TimeDTO();
        timeDTO.setId(entity.getId());
        timeDTO.setNome(entity.getNome());
        timeDTO.setSigla(entity.getSigla());
        return timeDTO;
    }

    public TimeDTO adicionarTime(NovoTimeDTO novoTimeDTO) {
        Time time = dtoToEntity(novoTimeDTO);
        return entityToDto(timeRepository.save(time));
    }

    private Time dtoToEntity(NovoTimeDTO novoTimeDTO) {
        Time time = new Time();
        return dtoToEntity(novoTimeDTO, time);
    }

    private Time dtoToEntity(NovoTimeDTO novoTimeDTO, Time time) {
        time.setEstado(novoTimeDTO.getEstado());
        time.setNome(novoTimeDTO.getNome());
        time.setSigla(novoTimeDTO.getSigla());
        return time;
    }

    public TimeDTO atualizarTime(Integer id, NovoTimeDTO novoTimeDTO) throws Exception {
        final Optional<Time> optionalTime = timeRepository.findById(id);
        if (optionalTime.isPresent()) {
            final Time time = optionalTime.get();
            return entityToDto(timeRepository.save(dtoToEntity(novoTimeDTO, time)));
        } else {
            throw new Exception(String.format("Time com id %d inexistente", id));
        }
    }
}
