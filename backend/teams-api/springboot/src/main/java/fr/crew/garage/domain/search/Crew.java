package fr.crew.garage.domain.search;

import fr.crew.garage.domain.skill.entity.SkillEntity;

import java.util.List;

public class Crew {

    private final String name;
    private final List<SkillEntity> skills;


    public Crew(String name, List<SkillEntity> skills) {
        this.name = name;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public List<SkillEntity> getSkills() {
        return skills;
    }
}
