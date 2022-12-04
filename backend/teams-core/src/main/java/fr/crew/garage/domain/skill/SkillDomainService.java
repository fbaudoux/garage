package fr.crew.garage.domain.skill;

import fr.crew.garage.domain.skill.entity.SkillEntity;
import fr.crew.garage.domain.skill.repository.SkillRepository;
import fr.crew.garage.domain.team.entity.TeammateEntity;
import fr.crew.garage.domain.team.repository.TeammateRepository;

public class SkillDomainService {


    final
    SkillRepository skillRepository;

    final
    TeammateRepository teammateRepository;

    public SkillDomainService(SkillRepository skillRepository, TeammateRepository teammateRepository) {
        this.skillRepository = skillRepository;
        this.teammateRepository = teammateRepository;
    }

    //@Transactional
    public SkillEntity createSkill(String name) {
        SkillEntity newEntity = new SkillEntity();
        newEntity.setName(name);
        return skillRepository.save(newEntity);
    }

    //@Transactional
    public void addSkillToTeammate(SkillEntity skill, TeammateEntity teammate) {
        teammate.addSkill(skill);
        teammateRepository.save(teammate);
    }

    //@Transactional
    public void removeSkillToTeammate(SkillEntity skill, TeammateEntity teammate) {
        teammate.removeSkill(skill);
        teammateRepository.save(teammate);

    }

}
