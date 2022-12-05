package fr.crew.garage.domain.search.entity;

import fr.crew.garage.domain.skill.entity.SkillEntity;

import java.util.ArrayList;
import java.util.List;


public class CrewSearchEntity {


    private String name;


    private Long id;


    private List<SkillEntity> skills;

    public CrewSearchEntity() {

    }


    public CrewSearchEntity(String name, List<SkillEntity> skills) {
        this.name = name;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public List<SkillEntity> getSkills() {
        if (skills == null) {
            skills = new ArrayList<>();
        }
        return skills;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSkills(List<SkillEntity> skills) {
        this.skills = skills;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
