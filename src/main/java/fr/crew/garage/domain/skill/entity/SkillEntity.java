package fr.crew.garage.domain.skill.entity;

import javax.persistence.*;

@Entity(
        name = "Skill"
)
@Table(
        name = "SKILL"
)
public class SkillEntity {
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
}




