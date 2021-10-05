package fr.crew.garage.api.team;

import fr.crew.garage.domain.team.TeamDomainService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class GetTeamUseCase {
    @Autowired
    TeamDomainService domainService;

    @Autowired
    ModelMapper modelMapper;

    @Transactional
    public TeamDTO execute(Long teamId) {
        return modelMapper.map(domainService.getTeam(teamId), TeamDTO.class);
    }
}