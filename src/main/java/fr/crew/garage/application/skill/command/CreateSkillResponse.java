package fr.crew.garage.application.skill.command;

import fr.crew.garage.domain.skill.entity.SkillEntity;

public class CreateSkillResponse {
 
    private final SkillEntity skill;

    public CreateSkillResponse(SkillEntity entity) {
        this.skill = entity;
    }

}
