package fr.crew.garage.api.team;

import fr.crew.garage.api.team.dto.TeamDTO;
import fr.crew.garage.domain.team.repository.TeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class GetAllTeamsUseCase {
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    ModelMapper modelMapper;

    public Collection<TeamDTO> execute() {

        Collection<TeamDTO> dtos = teamRepository.findAll()
                .stream()
                .map(team -> modelMapper.map(team, TeamDTO.class))
                .collect(Collectors.toList());
        return dtos;
    }
}