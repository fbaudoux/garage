package fr.crew.dojo.application.team.command;


public class GetTeamRequest {

    private final Long teamId;

    public GetTeamRequest(Long teamId) {
        this.teamId = teamId;
    }

    public Long getTeamId() {
        return teamId;
    }

}
