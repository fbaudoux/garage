package fr.crew.garage.domain.search;

import fr.crew.garage.domain.skill.entity.SkillEntity;

import java.util.List;

public class CrewSearch {

    private String name;
    private List<SkillEntity> skills;

    public CrewSearch() {

    }


    public CrewSearch(String name, List<SkillEntity> skills) {
        this.name = name;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public List<SkillEntity> getSkills() {
        return skills;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSkills(List<SkillEntity> skills) {
        this.skills = skills;
    }
}
