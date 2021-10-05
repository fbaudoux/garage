package fr.crew.garage.api.search;

import java.util.List;

public class CrewDTO {

    private final String name;
    List<CrewMemberDTO> members;

    public CrewDTO(List<CrewMemberDTO> members, String name) {
        this.members = members;
        this.name = name;
    }


    @Override
    public String toString() {
        return name + ":" +
                members;
    }
}
