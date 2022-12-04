package fr.crew.garage.api.skill;

import fr.crew.garage.domain.skill.entity.SkillEntity;
import fr.crew.garage.domain.skill.repository.SkillRepository;

import java.util.List;

public class GetAllSkillsUseCase {

    final
    SkillRepository skillRepository;
    
    public GetAllSkillsUseCase(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public List<SkillEntity> execute() {
        return skillRepository.findAll();
    }
}
