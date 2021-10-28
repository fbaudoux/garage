package fr.crew.garage.api.team;

import fr.crew.garage.api.team.dto.TeamDTO;
import fr.crew.garage.domain.team.repository.TeamRepository;
import org.springframework.stereotype.Component;

@Component
public class DeleteTeamUseCase {

    final
    TeamRepository teamRepository;

    public DeleteTeamUseCase(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public void execute(TeamDTO teamDTO) {
        teamRepository.deleteById(teamDTO.getId());
    }
}
