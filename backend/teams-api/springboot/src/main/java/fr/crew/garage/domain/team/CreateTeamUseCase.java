package fr.crew.garage.domain.team;

import fr.crew.garage.domain.team.entity.TeamEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateTeamUseCase {

    @Autowired
    TeamDomainService domainService;

    public TeamEntity execute(String name) {
        return domainService.createTeam(name);
    }
}