package fr.crew.garage.api.skill;

import fr.crew.garage.domain.skill.entity.SkillEntity;
import fr.crew.garage.domain.skill.repository.SkillRepository;

import javax.transaction.Transactional;

public class DeleteSkillUseCase {

    final
    SkillRepository skillRepository;

    public DeleteSkillUseCase(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Transactional
    public void execute(SkillEntity skillDTO) {
        skillRepository.deleteById(skillDTO.getId());
    }
}
