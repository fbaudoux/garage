package fr.crew.garage.domain.team;

import fr.crew.garage.domain.team.entity.TeamEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class GetAllTeamsUseCase {
    @Autowired
    TeamDomainService domainService;

    public Collection<TeamEntity> execute() {
        return domainService.getAllTeams();
    }
}