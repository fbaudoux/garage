package fr.crew.garage.domain.team.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
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


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "membership", joinColumns = @JoinColumn(name = "teamid"), inverseJoinColumns = @JoinColumn(name = "teammateid"))
    private Set<TeammateEntity> teammates;

    public Set<TeammateEntity> getTeammates() {
        return teammates;
    }

    public void addTeammate(TeammateEntity teammate) {
        this.teammates.add(teammate);
    }
}
