package fr.crew.garage.application.team;

import fr.crew.garage.application.team.command.CreateTeamRequest;
import fr.crew.garage.application.team.command.CreateTeamResponse;
import fr.crew.garage.domain.team.TeamDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateTeamUseCase {
 
    @Autowired
    TeamDomainService domainService;

    public CreateTeamResponse execute(CreateTeamRequest req) {

        return new CreateTeamResponse(domainService.createTeam(req.getName()));
    }
}