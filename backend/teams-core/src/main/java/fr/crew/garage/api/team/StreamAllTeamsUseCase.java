package fr.crew.garage.api.team;

import fr.crew.garage.domain.team.repository.TeamRepository;


public class StreamAllTeamsUseCase {
    final
    TeamRepository teamRepository;

    public StreamAllTeamsUseCase(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

}