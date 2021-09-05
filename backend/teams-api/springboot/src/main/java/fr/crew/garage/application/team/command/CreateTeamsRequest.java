package fr.crew.garage.application.team.command;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class CreateTeamsRequest {

    private final Integer numberOfTeamsToCreate;

    public CreateTeamsRequest(@Min(2) @Max(500) Integer numberOfTeamsToCreate) {
        this.numberOfTeamsToCreate = numberOfTeamsToCreate;
    }

    public Integer getNumberOfTeamsToCreate() {
        return numberOfTeamsToCreate;
    }
}
