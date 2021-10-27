package fr.crew.garage.api.skill;

import fr.crew.garage.api.skill.dto.SkillDTO;
import fr.crew.garage.domain.skill.repository.SkillRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetSkillUseCase {

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    ModelMapper modelMapper;

    public SkillDTO execute(Long id) {
        return modelMapper.map(skillRepository.getById(id), SkillDTO.class);
    }
}
