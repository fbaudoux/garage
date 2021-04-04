package fr.crew.garage.application.team.command;

import fr.crew.garage.domain.team.entity.TeamEntity;
import fr.crew.garage.domain.team.entity.TeammateEntity;

import java.util.Collection;

public class GetTeamResponse {

    private TeamEntity aTeam;
    private Collection<TeammateEntity> members;

    public GetTeamResponse(TeamEntity aTeam, Collection<TeammateEntity> members) {
        this.aTeam = aTeam;
        this.members = members;
    }

    public TeamEntity getaTeam() {
        return aTeam;
    }

    private void setaTeam(TeamEntity aTeam) {
        this.aTeam = aTeam;
    }

    public Collection<TeammateEntity> getMembers() {
        return members;
    }
}
