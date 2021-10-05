package fr.crew.garage.api.skill;

import fr.crew.garage.api.skill.dto.SkillDTO;
import fr.crew.garage.domain.skill.repository.SkillRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetAllSkillsUseCase {

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<SkillDTO> execute() {
        List<SkillDTO> dtos = skillRepository.findAll()
                .stream()
                .map(skill -> modelMapper.map(skill, SkillDTO.class))
                .collect(Collectors.toList());
        return dtos;
    }
}
