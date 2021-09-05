package fr.crew.garage.domain.team;

import fr.crew.garage.domain.team.entity.TeamEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class GetAllTeamsPageByPageUseCase {

    @Autowired
    TeamDomainService domainService;

    public Page<TeamEntity> execute(Integer pageNumber) {
        return domainService.getAllTeamsPageByPage(pageNumber);
    }
}