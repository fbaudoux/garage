package fr.crew.dojo.application.team.command;

import fr.crew.dojo.domain.team.entity.TeammateEntity;

public class CreateTeammateResponse {

    private final TeammateEntity createdTeammate;

    public CreateTeammateResponse(TeammateEntity teammate) {
        this.createdTeammate = teammate;
    }

    public TeammateEntity getCreatedTeammate() {
        return createdTeammate;
    }
}
