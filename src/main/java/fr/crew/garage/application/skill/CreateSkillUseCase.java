package fr.crew.garage.application.skill;

import fr.crew.garage.application.skill.command.CreateSkillRequest;
import fr.crew.garage.application.skill.command.CreateSkillResponse;
import fr.crew.garage.domain.skill.SkillDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateSkillUseCase {


    @Autowired
    SkillDomainService domainService;

    public CreateSkillResponse execute(CreateSkillRequest req) {

        return new CreateSkillResponse(domainService.createSkill(req.getName()));
    }

}
