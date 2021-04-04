package fr.crew.garage.application.team;

import fr.crew.garage.application.team.command.AddTeammateToTeamRequest;
import fr.crew.garage.application.team.command.AddTeammateToTeamResponse;
import fr.crew.garage.domain.team.TeamDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddTeammateToTeamUseCase {

    @Autowired
    TeamDomainService domainService;

    public AddTeammateToTeamResponse execute(AddTeammateToTeamRequest req) {

        domainService.addTeammateToTeam(req.getTeammateId(), req.getTeamId());
        return new AddTeammateToTeamResponse();
    }
}
