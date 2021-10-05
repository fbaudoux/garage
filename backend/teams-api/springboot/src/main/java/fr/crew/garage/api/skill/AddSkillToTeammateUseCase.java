package fr.crew.garage.api.skill;

import fr.crew.garage.api.skill.dto.SkillDTO;
import fr.crew.garage.api.team.TeammateDTO;
import fr.crew.garage.domain.skill.SkillDomainService;
import fr.crew.garage.domain.skill.repository.SkillRepository;
import fr.crew.garage.domain.team.repository.TeammateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class AddSkillToTeammateUseCase {

    @Autowired
    SkillDomainService domainService;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    TeammateRepository teammateRepository;

    @Transactional
    public void execute(SkillDTO skill, TeammateDTO mate) {
        domainService.addSkillToTeammate(skillRepository.getById(skill.getId()), teammateRepository.getById(mate.getId()));
    }

}
