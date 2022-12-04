package fr.crew.garage.domain.skill.repository;

import fr.crew.garage.domain.skill.entity.SkillEntity;

import java.util.List;

public interface SkillRepository {
    SkillEntity findByName(String name);

    SkillEntity getById(Long id);

    void deleteById(Long id);

    List<SkillEntity> findAll();

    SkillEntity save(SkillEntity newEntity);
}
