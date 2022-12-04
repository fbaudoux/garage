package fr.crew.garage.domain.search;

import java.util.List;

public class Crew {

    private final String name;
    private List<CrewMember> members;

    public Crew(List<CrewMember> members, String name) {
        this.members = members;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<CrewMember> getMembers() {
        return members;
    }

    @Override
    public String toString() {
        return name + ":" +
                members;
    }
}
