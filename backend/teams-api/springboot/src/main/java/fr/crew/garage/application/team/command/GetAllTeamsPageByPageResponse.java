package fr.crew.garage.application.team.command;

import fr.crew.garage.domain.team.entity.TeamEntity;
import org.springframework.data.domain.Page;

public class GetAllTeamsPageByPageResponse {

    private final Page<TeamEntity> allTeamsPageByPage;

    public GetAllTeamsPageByPageResponse(Page<TeamEntity> allTeamsPageByPage) {
        this.allTeamsPageByPage = allTeamsPageByPage;
    }

    public Page<TeamEntity> getAllTeamsPageByPage() {
        return allTeamsPageByPage;
    }
}
