package fr.crew.dojo.application.team.command;


public class CreateTeamRequest {

    private final String name;

    public CreateTeamRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
