package fr.crew.garage.api.skill;

import fr.crew.garage.api.skill.dto.SkillDTO;
import fr.crew.garage.api.team.dto.TeammateDTO;
import fr.crew.garage.domain.skill.SkillDomainService;
import fr.crew.garage.domain.skill.repository.SkillRepository;
import fr.crew.garage.domain.team.repository.TeammateRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class AddSkillToTeammateUseCase {

    final
    SkillDomainService domainService;

    final
    SkillRepository skillRepository;

    final
    TeammateRepository teammateRepository;

    public AddSkillToTeammateUseCase(SkillDomainService domainService, SkillRepository skillRepository, TeammateRepository teammateRepository) {
        this.domainService = domainService;
        this.skillRepository = skillRepository;
        this.teammateRepository = teammateRepository;
    }

    @Transactional
    public void execute(SkillDTO skill, TeammateDTO mate) {
        domainService.addSkillToTeammate(skillRepository.getById(skill.getId()), teammateRepository.getById(mate.getId()));
    }

}
