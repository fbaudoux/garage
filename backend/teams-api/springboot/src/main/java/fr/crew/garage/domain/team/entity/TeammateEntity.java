package fr.crew.garage.domain.team.entity;

import fr.crew.garage.domain.skill.entity.SkillEntity;

import javax.persistence.*;
import java.util.List;


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


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "teammate_skill",
            joinColumns = { @JoinColumn(name = "teammate_fk") },
            inverseJoinColumns = { @JoinColumn(name = "skill_fk") })
    List<SkillEntity> skills;

    public List<SkillEntity> getSkills() {
        return skills;
    }

    public void addSkill(SkillEntity skill) {
        this.skills.add(skill);
        skill.giveSkillToTeammates(this);
    }

    public void removeSkill(SkillEntity skill) {
        this.skills.remove(skill);
        skill.removeSkillToTeammates(this);
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
}
