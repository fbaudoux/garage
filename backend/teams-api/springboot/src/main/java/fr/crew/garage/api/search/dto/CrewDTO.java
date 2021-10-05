package fr.crew.garage.api.search.dto;

import java.util.List;

public class CrewDTO {

    private String name;
    private List<CrewMemberDTO> members;

    public CrewDTO() {

    }

    public CrewDTO(List<CrewMemberDTO> members, String name) {
        this.members = members;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMembers(List<CrewMemberDTO> members) {
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public List<CrewMemberDTO> getMembers() {
        return members;
    }

    @Override
    public String toString() {
        return name + ":" +
                members;
    }
}
