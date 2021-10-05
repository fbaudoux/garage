package fr.crew.garage.domain.team.entity;

import fr.crew.garage.domain.skill.entity.SkillEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity(
        name = "Teammate"
)
@Table(
        name = "TEAMMATE"
)
public class TeammateEntity {

    @Id
    @GeneratedValue
    @Column(
            name = "ID"
    )
    private Long id;


    @Column(
            name = "NAME"
    )
    private String name;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "teammate_skill",
            joinColumns = {@JoinColumn(name = "teammate_fk")},
            inverseJoinColumns = {@JoinColumn(name = "skill_fk")})
    Set<SkillEntity> skills;

    public Set<SkillEntity> getSkills() {
        return skills;
    }

    public void addSkill(SkillEntity skill) {
        if (this.skills == null) {
            this.skills = new HashSet<>();
        }
        this.skills.add(skill);
        skill.giveSkillToTeammates(this);
    }

    public void removeSkill(SkillEntity skill) {
        this.skills.remove(skill);
        skill.removeSkillToTeammates(this);
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
