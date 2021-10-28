package fr.crew.garage.api.skill;

import fr.crew.garage.api.skill.dto.SkillDTO;
import fr.crew.garage.domain.skill.SkillDomainService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CreateSkillUseCase {

    final
    SkillDomainService domainService;

    final
    ModelMapper modelMapper;

    public CreateSkillUseCase(SkillDomainService domainService, ModelMapper modelMapper) {
        this.domainService = domainService;
        this.modelMapper = modelMapper;
    }

    public SkillDTO execute(SkillDTO skillDTO) {

        return modelMapper.map(domainService.createSkill(skillDTO.getName()), SkillDTO.class);
    }

}
