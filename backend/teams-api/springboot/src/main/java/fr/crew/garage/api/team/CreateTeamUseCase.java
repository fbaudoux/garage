package fr.crew.garage.api.team;

import fr.crew.garage.domain.team.TeamDomainService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateTeamUseCase {

    @Autowired
    TeamDomainService domainService;

    @Autowired
    ModelMapper modelMapper;

    public TeamDTO execute(String name) {

        return modelMapper.map(domainService.createTeam(name), TeamDTO.class);
    }
}