package fr.crew.garage.api.team;

import fr.crew.garage.domain.team.TeamDomainService;
import fr.crew.garage.domain.team.repository.TeamRepository;
import fr.crew.garage.domain.team.repository.TeammateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class AddTeammateToTeamUseCase {

    @Autowired
    TeamDomainService domainService;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    TeammateRepository teammateRepository;

    @Transactional
    public void execute(AddTeammateToTeamRequest req) {
        domainService.addTeammateToTeam(teammateRepository.getOne(req.getTeammateId()), teamRepository.getOne(req.getTeamId()));
    }
}
