package fr.crew.garage.domain.team;

import fr.crew.garage.domain.team.entity.TeammateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class GetAllTeammatesUseCase {

    @Autowired
    TeamDomainService domainService;

    public Collection<TeammateEntity> execute() {
        return domainService.getAllTeammates();
    }
}