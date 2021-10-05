package fr.crew.garage.domain.skill.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.crew.garage.domain.team.entity.TeammateEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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


    @ManyToMany(mappedBy = "skills", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<TeammateEntity> teammatesHavingSkill = new ArrayList<>();

    public List<TeammateEntity> getTeammatesHavingSkill() {
        return teammatesHavingSkill;
    }

    public void giveSkillToTeammates(TeammateEntity skilledTeammate) {
        this.teammatesHavingSkill.add(skilledTeammate);
    }

    public void removeSkillToTeammates(TeammateEntity skilledTeammate) {
        this.teammatesHavingSkill.remove(skilledTeammate);
    }

    public void removeSkillToAllTeammates() {
        this.teammatesHavingSkill = new ArrayList<>();
    }

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




