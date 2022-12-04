package fr.crew.garage.domain.search.entity;

import fr.crew.garage.domain.skill.entity.SkillEntity;

import java.util.Set;


public class CrewSearchEntity {


    private String name;


    private Long id;


    private Set<SkillEntity> skills;

    public CrewSearchEntity() {

    }


    public CrewSearchEntity(String name, Set<SkillEntity> skills) {
        this.name = name;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public Set<SkillEntity> getSkills() {
        return skills;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSkills(Set<SkillEntity> skills) {
        this.skills = skills;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
