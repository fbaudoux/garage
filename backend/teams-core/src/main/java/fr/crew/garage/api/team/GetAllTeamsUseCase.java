package fr.crew.garage.api.team;

import fr.crew.garage.domain.team.entity.TeamEntity;
import fr.crew.garage.domain.team.repository.TeamRepository;

import java.util.Collection;

public class GetAllTeamsUseCase {
    final
    TeamRepository teamRepository;
    
    public GetAllTeamsUseCase(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Collection<TeamEntity> execute() {
        return teamRepository.findAll();
    }
}