package fr.crew.garage.domain.skill.repository;

import fr.crew.garage.domain.skill.entity.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<SkillEntity, Long> {
    SkillEntity findByName(String name);
}
