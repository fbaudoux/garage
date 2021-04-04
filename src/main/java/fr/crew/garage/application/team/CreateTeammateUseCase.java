package fr.crew.garage.application.team;

import fr.crew.garage.application.team.command.CreateTeammateRequest;
import fr.crew.garage.application.team.command.CreateTeammateResponse;
import fr.crew.garage.domain.team.TeamDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateTeammateUseCase {

    @Autowired
    TeamDomainService domainService;
    public CreateTeammateResponse execute(CreateTeammateRequest req) {

        return new CreateTeammateResponse(domainService.createTeammate(req.getName()));

    }
}