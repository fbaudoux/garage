package fr.crew.garage.application.skill.command;

import javax.validation.Valid;

public class CreateSkillRequest {

    private final String name;

    public CreateSkillRequest(@Valid String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
