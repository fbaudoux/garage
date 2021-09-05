package fr.crew.garage.domain.skill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.crew.garage.domain.skill.entity.SkillEntity;

@Component
public class CreateSkillUseCase {

    @Autowired
    SkillDomainService domainService;

    public SkillEntity execute(String name) {

        return domainService.createSkill(name);
    }

}
