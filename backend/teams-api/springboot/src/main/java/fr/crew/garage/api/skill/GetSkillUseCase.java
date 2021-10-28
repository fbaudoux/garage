package fr.crew.garage.api.skill;

import fr.crew.garage.api.skill.dto.SkillDTO;
import fr.crew.garage.domain.skill.repository.SkillRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GetSkillUseCase {

    final
    SkillRepository skillRepository;

    final
    ModelMapper modelMapper;

    public GetSkillUseCase(SkillRepository skillRepository, ModelMapper modelMapper) {
        this.skillRepository = skillRepository;
        this.modelMapper = modelMapper;
    }

    public SkillDTO execute(Long id) {
        return modelMapper.map(skillRepository.getById(id), SkillDTO.class);
    }
}
