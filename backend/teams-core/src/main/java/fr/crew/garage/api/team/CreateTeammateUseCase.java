package fr.crew.garage.api.team;

import fr.crew.garage.domain.team.TeamDomainService;
import fr.crew.garage.domain.team.entity.TeammateEntity;

public class CreateTeammateUseCase {

    final
    TeamDomainService domainService;


    public CreateTeammateUseCase(TeamDomainService domainService) {
        this.domainService = domainService;
    }

    public TeammateEntity execute(TeammateEntity mate) {
        return domainService.createTeammate(mate);
    }
}