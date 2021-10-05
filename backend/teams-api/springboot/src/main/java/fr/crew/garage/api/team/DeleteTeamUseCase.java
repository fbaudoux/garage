package fr.crew.garage.api.team;

import fr.crew.garage.domain.team.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteTeamUseCase {

    @Autowired
    TeamRepository teamRepository;

    public void execute(TeamDTO teamDTO) {
        teamRepository.deleteById(teamDTO.getId());
    }
}
