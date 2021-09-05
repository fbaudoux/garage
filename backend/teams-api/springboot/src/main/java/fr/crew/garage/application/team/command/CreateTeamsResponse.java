package fr.crew.garage.application.team.command;

import fr.crew.garage.domain.team.entity.TeamEntity;

import java.util.HashSet;
import java.util.Set;

public class CreateTeamsResponse {

    private Set<TeamEntity> createdTeams = null;

    public CreateTeamsResponse() {
        this.createdTeams = new HashSet<>();
    }

    public Set<TeamEntity> getCreatedTeams() {
        return createdTeams;
    }

    public void setCreatedTeams(Set<TeamEntity> createdTeams) {
        this.createdTeams = createdTeams;
    }

    public void addTeam(TeamEntity teamWithRandomName) {
        this.createdTeams.add(teamWithRandomName);
    }
}
