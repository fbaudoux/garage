package fr.crew.garage.domain.skill;

import fr.crew.garage.domain.skill.entity.SkillEntity;
import fr.crew.garage.domain.team.entity.TeammateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddSkillToTeammateUseCase {

    @Autowired
    SkillDomainService domainService;

    public void execute(SkillEntity skill, TeammateEntity mate) {

        domainService.addSkillToTeammate(skill, mate);
    }

}
