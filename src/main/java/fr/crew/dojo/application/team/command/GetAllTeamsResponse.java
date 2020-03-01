package fr.crew.dojo.application.team.command;

import fr.crew.dojo.domain.team.entity.TeamEntity;

import java.io.Serializable;
import java.util.Collection;


public class GetAllTeamsResponse implements Serializable {

    private Collection<TeamEntity> teams;

    public GetAllTeamsResponse(Collection<TeamEntity> teams) {
        this.teams = teams;
    }

    public Collection<TeamEntity> getTeams() {
        return teams;
    }

    private void setTeams(Collection<TeamEntity> teams) {
        this.teams = teams;
    }
}
