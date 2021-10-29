package fr.crew.garage.domain.search.entity;

import fr.crew.garage.domain.skill.entity.SkillEntity;

import javax.persistence.*;
import java.util.Set;

@Entity(
        name = "CrewSearch"
)
@Table(
        name = "CREW_SEARCH"
)
public class CrewSearchEntity {

    @Column(
            name = "NAME"
    )
    private String name;

    @Id
    @GeneratedValue
    @Column(
            name = "ID"
    )
    private Long id;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "crew_search_skills", joinColumns = @JoinColumn(name = "crewsearchid"), inverseJoinColumns = @JoinColumn(name = "skillid"))
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
