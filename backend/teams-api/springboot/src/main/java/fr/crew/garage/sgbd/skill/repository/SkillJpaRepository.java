package fr.crew.garage.sgbd.skill.repository;

import fr.crew.garage.sgbd.skill.entity.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillJpaRepository extends JpaRepository<SkillEntity, Long> {
    SkillEntity findByName(String name);
}
