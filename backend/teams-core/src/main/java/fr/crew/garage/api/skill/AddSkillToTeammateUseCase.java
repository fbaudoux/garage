package fr.crew.garage.api.skill;

import fr.crew.garage.domain.skill.SkillDomainService;
import fr.crew.garage.domain.skill.entity.SkillEntity;
import fr.crew.garage.domain.skill.repository.SkillRepository;
import fr.crew.garage.domain.team.entity.TeammateEntity;
import fr.crew.garage.domain.team.repository.TeammateRepository;


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

    //@Transactional
    public void execute(SkillEntity skill, TeammateEntity mate) {
        domainService.addSkillToTeammate(skillRepository.getById(skill.getId()), teammateRepository.getById(mate.getId()));
    }

}
