package fr.crew.garage.api.team;

import fr.crew.garage.api.team.dto.TeamDTO;
import fr.crew.garage.domain.team.repository.TeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class GetAllTeamsUseCase {
    final
    TeamRepository teamRepository;

    final
    ModelMapper modelMapper;

    public GetAllTeamsUseCase(TeamRepository teamRepository, ModelMapper modelMapper) {
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
    }

    public Collection<TeamDTO> execute() {

        Collection<TeamDTO> dtos = teamRepository.findAll()
                .stream()
                .map(team -> modelMapper.map(team, TeamDTO.class))
                .collect(Collectors.toList());
        return dtos;
    }
}