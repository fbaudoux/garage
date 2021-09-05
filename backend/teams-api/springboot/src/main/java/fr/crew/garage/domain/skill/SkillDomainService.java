package fr.crew.garage.domain.skill;

import fr.crew.garage.domain.skill.entity.SkillEntity;
import fr.crew.garage.domain.skill.repository.SkillRepository;
import fr.crew.garage.domain.team.entity.TeammateEntity;
import fr.crew.garage.domain.team.repository.TeammateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SkillDomainService {

    Logger logger = LoggerFactory.getLogger(SkillDomainService.class);

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    TeammateRepository teammateRepository;

    @Transactional
    public SkillEntity createSkill(String name) {
        logger.info("A new skill is created : " + name);
        SkillEntity newEntity = new SkillEntity();
        newEntity.setName(name);
        skillRepository.save(newEntity);
        return newEntity;
    }

    @Transactional
    public void removeSkill(String name) {
        skillRepository.delete(skillRepository.findByName(name));
    }


    @Transactional
    public void addSkillToTeammate(SkillEntity skill , TeammateEntity teammate){
        teammate.addSkill(skill);
        teammateRepository.save(teammate);
    }

    @Transactional
    public void removeSkillToTeammate(SkillEntity skill , TeammateEntity teammate){
        teammate.removeSkill(skill);
        teammateRepository.save(teammate);

    }

}
