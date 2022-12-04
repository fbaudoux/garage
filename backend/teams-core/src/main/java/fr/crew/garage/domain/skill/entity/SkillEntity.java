package fr.crew.garage.domain.skill.entity;

import fr.crew.garage.domain.team.entity.TeammateEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class SkillEntity {

    private Long id;
    private String name;

    public SkillEntity() {
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
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SkillEntity that = (SkillEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}




