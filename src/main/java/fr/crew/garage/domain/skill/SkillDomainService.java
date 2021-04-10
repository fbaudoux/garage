package fr.crew.garage.domain.skill;

import fr.crew.garage.domain.skill.entity.SkillEntity;
import fr.crew.garage.domain.skill.repository.SkillRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillDomainService {

    Logger logger = LoggerFactory.getLogger(SkillDomainService.class);

    @Autowired
    SkillRepository skillRepository;

    public SkillEntity createSkill(String name) {
        logger.info("A new skill is created : " + name);
        SkillEntity newEntity = new SkillEntity();
        newEntity.setName(name);
        skillRepository.save(newEntity);
        return newEntity;
    }

}
