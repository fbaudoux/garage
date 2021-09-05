package fr.crew.garage.domain.team;

import fr.crew.garage.domain.team.entity.TeammateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateTeammateUseCase {

    @Autowired
    TeamDomainService domainService;

    public TeammateEntity execute(String name) {
        return domainService.createTeammate(name);
    }
}