package fr.crew.garage.api.skill;

import fr.crew.garage.domain.skill.entity.SkillEntity;
import fr.crew.garage.domain.skill.repository.SkillRepository;

public class GetSkillUseCase {

    final
    SkillRepository skillRepository;


    public GetSkillUseCase(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public SkillEntity execute(Long id) {
        return skillRepository.getById(id);
    }
}
