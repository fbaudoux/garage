package fr.crew.dojo.application.team.command;

public class CreateTeammateRequest {

    private final String name;


    public CreateTeammateRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
