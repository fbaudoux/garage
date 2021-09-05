package fr.crew.garage.domain.team;

import fr.crew.garage.domain.team.entity.TeamEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetTeamUseCase {
    @Autowired
    TeamDomainService domainService;

    public TeamEntity execute(Long teamId) {
        return domainService.getTeam(teamId);
    }
}