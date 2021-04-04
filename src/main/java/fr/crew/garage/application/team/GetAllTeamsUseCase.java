package fr.crew.garage.application.team;

import fr.crew.garage.application.team.command.GetAllTeamsResponse;
import fr.crew.garage.domain.team.TeamDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllTeamsUseCase {
    @Autowired
    TeamDomainService domainService;

    public GetAllTeamsResponse execute() {
        return new GetAllTeamsResponse(domainService.getAllTeams());
    }
}