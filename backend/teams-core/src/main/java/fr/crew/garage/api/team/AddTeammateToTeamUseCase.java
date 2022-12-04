package fr.crew.garage.api.team;

import fr.crew.garage.domain.team.TeamDomainService;
import fr.crew.garage.domain.team.entity.TeamEntity;
import fr.crew.garage.domain.team.entity.TeammateEntity;
import fr.crew.garage.domain.team.repository.TeamRepository;
import fr.crew.garage.domain.team.repository.TeammateRepository;

import javax.transaction.Transactional;

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
    public void execute(TeammateEntity mate, TeamEntity team) {
        domainService.addTeammateToTeam(teammateRepository.getById(mate.getId()), teamRepository.getById(team.getId()));
    }
}
