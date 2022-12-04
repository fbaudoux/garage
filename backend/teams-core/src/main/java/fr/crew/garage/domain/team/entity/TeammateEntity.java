package fr.crew.garage.domain.team.entity;

import fr.crew.garage.domain.skill.entity.SkillEntity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class TeammateEntity {


    private Long id;
    private String name;
    Set<SkillEntity> skills;

    public Set<SkillEntity> getSkills() {
        if (this.skills == null) {
            this.skills = new HashSet<>();
        }
        return skills;
    }

    public void setSkills(Set<SkillEntity> skills) {
        this.skills = skills;
    }

    public void addSkill(SkillEntity skill) {
        if (this.skills == null) {
            this.skills = new HashSet<>();
        }
        this.skills.add(skill);
    }

    public void removeSkill(SkillEntity skill) {
        this.skills.remove(skill);
    }

    public void justRemoveSkill(SkillEntity skill) {
        this.skills.remove(skill);
    }


    public TeammateEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeammateEntity that = (TeammateEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @Override
    public String toString() {
        return name;

    }
}
