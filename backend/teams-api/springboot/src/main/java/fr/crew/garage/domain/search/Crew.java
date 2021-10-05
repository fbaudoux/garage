package fr.crew.garage.domain.search;

import java.util.List;

public class Crew {

    private final String name;
    List<CrewMember> members;

    public Crew(List<CrewMember> members, String name) {
        this.members = members;
        this.name = name;
    }


    @Override
    public String toString() {
        return name + ":" +
                members;
    }
}
