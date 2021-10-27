package fr.crew.garage.domain.team.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(
        name = "Team"
)
@Table(
        name = "TEAM"
)
public class TeamEntity {
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

    public TeamEntity() {
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


    public void setTeammates(Set<TeammateEntity> teammates) {
        this.teammates = teammates;
    }

    @ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinTable(name = "membership", joinColumns = @JoinColumn(name = "teamid"), inverseJoinColumns = @JoinColumn(name = "teammateid"))
    private Set<TeammateEntity> teammates = new HashSet<>();

    public Set<TeammateEntity> getTeammates() {
        return teammates;
    }

    public void addTeammate(TeammateEntity teammate) {
        this.teammates.add(teammate);
    }
}
