package fr.crew.garage.api.team;

import fr.crew.garage.domain.team.entity.TeamEntity;
import fr.crew.garage.domain.team.repository.TeamRepository;

public class DeleteTeamUseCase {

    final
    TeamRepository teamRepository;

    public DeleteTeamUseCase(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public void execute(TeamEntity teamDTO) {
        teamRepository.deleteById(teamDTO.getId());
    }
}
