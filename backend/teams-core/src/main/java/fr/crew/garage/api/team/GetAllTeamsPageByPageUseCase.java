package fr.crew.garage.api.team;

import fr.crew.garage.domain.team.TeamDomainService;
import fr.crew.garage.domain.team.entity.TeamEntity;

import java.util.List;

public class GetAllTeamsPageByPageUseCase {

    final
    TeamDomainService domainService;


    public GetAllTeamsPageByPageUseCase(TeamDomainService domainService) {
        this.domainService = domainService;
    }

    public List<TeamEntity> execute(Integer pageNumber) {
        return domainService.getAllTeamsPageByPage(pageNumber);
    }
}