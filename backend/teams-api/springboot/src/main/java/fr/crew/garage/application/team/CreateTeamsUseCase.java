package fr.crew.garage.application.team;

import fr.crew.garage.application.team.command.CreateTeamsRequest;
import fr.crew.garage.application.team.command.CreateTeamsResponse;
import fr.crew.garage.domain.team.TeamDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateTeamsUseCase {

    @Autowired
    TeamDomainService domainService;

    public CreateTeamsResponse execute(CreateTeamsRequest createTeamsRequest) {

        CreateTeamsResponse result = new CreateTeamsResponse();
        for (int i = 0; i < createTeamsRequest.getNumberOfTeamsToCreate(); i++) {
            result.addTeam(domainService.createTeamWithRandomName());
        }
        return result;

    }
}