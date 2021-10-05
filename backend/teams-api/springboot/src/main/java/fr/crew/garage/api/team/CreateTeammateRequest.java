package fr.crew.garage.api.team;

public class CreateTeammateRequest {

    private final String name;


    public CreateTeammateRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
