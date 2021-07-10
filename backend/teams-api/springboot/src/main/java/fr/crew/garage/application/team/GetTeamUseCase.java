package fr.crew.garage.application.team;

import fr.crew.garage.application.team.command.GetTeamRequest;
import fr.crew.garage.application.team.command.GetTeamResponse;
import fr.crew.garage.domain.team.TeamDomainService;
import fr.crew.garage.domain.team.entity.TeamEntity;
import fr.crew.garage.domain.team.entity.TeammateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class GetTeamUseCase {
    @Autowired
    TeamDomainService domainService;

    public GetTeamResponse execute(GetTeamRequest req) {

        TeamEntity aTeam = domainService.getTeam(req.getTeamId());
        Collection<TeammateEntity> members = domainService.getTeammatesForTeam(aTeam);
        return new GetTeamResponse(aTeam, members);
    }
}