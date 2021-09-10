package fr.crew.garage.domain.skill;

import fr.crew.garage.domain.skill.entity.SkillEntity;
import fr.crew.garage.domain.skill.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllSkillsUseCase {

    @Autowired
    SkillRepository skillRepository;

    public List<SkillEntity> execute() {
        return skillRepository.findAll();
    }
}
