package fr.crew.garage.infrastructure.rest.team;

import fr.crew.garage.application.skill.CreateSkillUseCase;
import fr.crew.garage.application.skill.command.CreateSkillRequest;
import fr.crew.garage.application.skill.command.CreateSkillResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SkillController {


    @Autowired
    CreateSkillUseCase createSkillUseCase;

    @ApiOperation(value = "createSkill", notes = "Create a new skill with a name given in parameter, this skill could then be assigned to teammates")
    @PostMapping({"/skills/"})
    public CreateSkillResponse createSkill(@Valid @RequestBody String name) {
        return createSkillUseCase.execute(new CreateSkillRequest(name));
    }

}
