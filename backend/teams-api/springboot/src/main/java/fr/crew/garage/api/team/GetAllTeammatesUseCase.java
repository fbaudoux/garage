package fr.crew.garage.api.team;

import fr.crew.garage.domain.team.TeamDomainService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class GetAllTeammatesUseCase {

    @Autowired
    TeamDomainService domainService;

    @Autowired
    ModelMapper modelMapper;

    public Collection<TeammateDTO> execute() {

        Collection<TeammateDTO> dtos = domainService.getAllTeammates()
                .stream()
                .map(teammate -> modelMapper.map(teammate, TeammateDTO.class))
                .collect(Collectors.toList());

        return dtos;
    }
}