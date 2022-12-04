package fr.crew.garage.api.team;

import fr.crew.garage.domain.team.TeamDomainService;
import fr.crew.garage.domain.team.entity.TeamEntity;

public class CreateTeamUseCase {

    final
    TeamDomainService domainService;


    public CreateTeamUseCase(TeamDomainService domainService) {
        this.domainService = domainService;
    }

    public TeamEntity execute(TeamEntity teamDTO) {

        TeamEntity teamEntity = domainService.createTeam(teamDTO);
        return teamEntity;
    }
}