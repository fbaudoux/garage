package fr.crew.dojo.application.team.command;

import fr.crew.dojo.domain.team.entity.TeamEntity;

public class CreateTeamResponse {

    private final TeamEntity createdTeam;


    public CreateTeamResponse(TeamEntity createdTeam) {
        this.createdTeam = createdTeam;
    }

    public TeamEntity getCreatedTeam() {
        return createdTeam;
    }


}
