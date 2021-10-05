package fr.crew.garage.api.team.dto;

import java.util.HashSet;
import java.util.Set;

public class TeamDTO {

    private Long id;
    private String name;
    private Set<TeammateDTO> teammates = new HashSet<>();

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

    public Set<TeammateDTO> getTeammates() {
        return teammates;
    }

    public void setTeammates(Set<TeammateDTO> teammates) {
        this.teammates = teammates;
    }
}
