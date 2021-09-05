package fr.crew.garage.application.team;

import fr.crew.garage.application.team.command.AddTeammateToTeamRequest;
import fr.crew.garage.application.team.command.AddTeammateToTeamResponse;
import fr.crew.garage.domain.team.TeamDomainService;
import fr.crew.garage.domain.team.repository.TeamRepository;
import fr.crew.garage.domain.team.repository.TeammateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddTeammateToTeamUseCase {

    @Autowired
    TeamDomainService domainService;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    TeammateRepository teammateRepository;

    public AddTeammateToTeamResponse execute(AddTeammateToTeamRequest req) {
        domainService.addTeammateToTeam(teammateRepository.getOne(req.getTeammateId()), teamRepository.getOne(req.getTeamId()));
        return new AddTeammateToTeamResponse();
    }
}
