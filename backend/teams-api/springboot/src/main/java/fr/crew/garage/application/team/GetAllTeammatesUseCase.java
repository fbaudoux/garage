package fr.crew.garage.application.team;

import fr.crew.garage.application.team.command.GetAllTeammatesResponse;
import fr.crew.garage.domain.team.TeamDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllTeammatesUseCase {

    @Autowired
    TeamDomainService domainService;

    public GetAllTeammatesResponse execute() {
        return new GetAllTeammatesResponse(domainService.getAllTeammates());
    }
}