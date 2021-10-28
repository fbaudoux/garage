package fr.crew.garage.api.team;

import fr.crew.garage.api.team.dto.TeamDTO;
import fr.crew.garage.api.team.dto.TeammateDTO;
import fr.crew.garage.domain.team.TeamDomainService;
import fr.crew.garage.domain.team.repository.TeamRepository;
import fr.crew.garage.domain.team.repository.TeammateRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class AddTeammateToTeamUseCase {

    final
    TeamDomainService domainService;

    final
    TeamRepository teamRepository;

    final
    TeammateRepository teammateRepository;

    public AddTeammateToTeamUseCase(TeamDomainService domainService, TeamRepository teamRepository, TeammateRepository teammateRepository) {
        this.domainService = domainService;
        this.teamRepository = teamRepository;
        this.teammateRepository = teammateRepository;
    }

    @Transactional
    public void execute(TeammateDTO mate, TeamDTO team) {
        domainService.addTeammateToTeam(teammateRepository.getById(mate.getId()), teamRepository.getById(team.getId()));
    }
}
