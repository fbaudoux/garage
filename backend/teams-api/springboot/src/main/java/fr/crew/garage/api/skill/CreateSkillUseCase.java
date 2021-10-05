package fr.crew.garage.api.skill;

import fr.crew.garage.api.skill.dto.SkillDTO;
import fr.crew.garage.domain.skill.SkillDomainService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateSkillUseCase {

    @Autowired
    SkillDomainService domainService;

    @Autowired
    ModelMapper modelMapper;

    public SkillDTO execute(SkillDTO skillDTO) {

        return modelMapper.map(domainService.createSkill(skillDTO.getName()), SkillDTO.class);
    }

}
