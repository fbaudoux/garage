package fr.crew.garage.api.team;

public class AddTeammateToTeamRequest {

    private Long teammateId;
    private Long teamId;

    public AddTeammateToTeamRequest(Long teammateId, Long teamId) {
        this.teammateId = teammateId;
        this.teamId = teamId;
    }

    public Long getTeammateId() {
        return teammateId;
    }

    public Long getTeamId() {
        return teamId;
    }

}
