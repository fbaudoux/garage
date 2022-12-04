package fr.crew.garage.api.team;

import fr.crew.garage.domain.team.entity.TeamEntity;
import fr.crew.garage.domain.team.repository.TeamRepository;

import javax.transaction.Transactional;

public class GetTeamUseCase {
    final
    TeamRepository teamRepository;
    
    public GetTeamUseCase(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Transactional
    public TeamEntity execute(Long teamId) {
        return teamRepository.getById(teamId);
    }
}