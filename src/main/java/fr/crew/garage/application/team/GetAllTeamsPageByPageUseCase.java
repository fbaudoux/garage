package fr.crew.garage.application.team;

import fr.crew.garage.application.team.command.GetAllTeamsPageByPageResponse;
import fr.crew.garage.domain.team.TeamDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllTeamsPageByPageUseCase {

    @Autowired
    TeamDomainService domainService;

    public GetAllTeamsPageByPageResponse execute(Integer pageNumber) {
        return new GetAllTeamsPageByPageResponse(domainService.getAllTeamsPageByPage(pageNumber));
    }
}