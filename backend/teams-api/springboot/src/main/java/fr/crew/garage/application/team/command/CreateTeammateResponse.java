package fr.crew.garage.application.team.command;

import fr.crew.garage.domain.team.entity.TeammateEntity;

public class CreateTeammateResponse {

    private final TeammateEntity createdTeammate;

    public CreateTeammateResponse(TeammateEntity teammate) {
        this.createdTeammate = teammate;
    }

    public TeammateEntity getCreatedTeammate() {
        return createdTeammate;
    }
}
