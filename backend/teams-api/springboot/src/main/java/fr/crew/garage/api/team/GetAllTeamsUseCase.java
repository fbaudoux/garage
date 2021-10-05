package fr.crew.garage.api.team;

import fr.crew.garage.domain.team.TeamDomainService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class GetAllTeamsUseCase {
    @Autowired
    TeamDomainService domainService;

    @Autowired
    ModelMapper modelMapper;

    public Collection<TeamDTO> execute() {

        Collection<TeamDTO> dtos = domainService.getAllTeams()
                .stream()
                .map(team -> modelMapper.map(team, TeamDTO.class))
                .collect(Collectors.toList());
        return dtos;
    }
}