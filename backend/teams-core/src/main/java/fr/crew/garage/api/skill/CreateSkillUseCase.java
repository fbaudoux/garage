package fr.crew.garage.api.skill;

import fr.crew.garage.domain.skill.SkillDomainService;
import fr.crew.garage.domain.skill.entity.SkillEntity;
import org.modelmapper.ModelMapper;

public class CreateSkillUseCase {

    final
    SkillDomainService domainService;

    final
    ModelMapper modelMapper;

    public CreateSkillUseCase(SkillDomainService domainService) {
        this.domainService = domainService;
        this.modelMapper = new ModelMapper();
    }

    public SkillEntity execute(SkillEntity skillDTO) {

        return domainService.createSkill(skillDTO.getName());
    }

}
