package fr.crew.garage.domain.team.entity;


import java.util.HashSet;
import java.util.Set;

public class TeamEntity {
    private Long id;


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

    private Set<TeammateEntity> teammates = new HashSet<>();

    public Set<TeammateEntity> getTeammates() {
        return teammates;
    }

    public void addTeammate(TeammateEntity teammate) {
        this.teammates.add(teammate);
    }
}
