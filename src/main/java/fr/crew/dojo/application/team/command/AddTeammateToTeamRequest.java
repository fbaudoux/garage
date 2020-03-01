package fr.crew.dojo.application.team.command;

public class AddTeammateToTeamRequest {

    private final Long teamId;
    private final Long teammateId;

    public AddTeammateToTeamRequest(Long teammateId, Long teamId) {
        this.teamId = teamId;
        this.teammateId = teammateId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public Long getTeammateId() {
        return teammateId;
    }
}
