package fr.crew.garage.api.skill;

import fr.crew.garage.api.skill.dto.SkillDTO;
import fr.crew.garage.domain.skill.repository.SkillRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetAllSkillsUseCase {

    final
    SkillRepository skillRepository;

    final
    ModelMapper modelMapper;

    public GetAllSkillsUseCase(SkillRepository skillRepository, ModelMapper modelMapper) {
        this.skillRepository = skillRepository;
        this.modelMapper = modelMapper;
    }

    public List<SkillDTO> execute() {
        List<SkillDTO> dtos = skillRepository.findAll()
                .stream()
                .map(skill -> modelMapper.map(skill, SkillDTO.class))
                .collect(Collectors.toList());
        return dtos;
    }
}
