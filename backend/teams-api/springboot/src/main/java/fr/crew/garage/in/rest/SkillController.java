package fr.crew.garage.in.rest;

import fr.crew.garage.api.skill.CreateSkillUseCase;
import fr.crew.garage.api.skill.dto.SkillDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Api(value = "Skills", tags = {"Skills"})
public class SkillController {


    @Autowired
    CreateSkillUseCase createSkillUseCase;

    @ApiOperation(value = "createSkill", notes = "Create a new skill with a name given in parameter, this skill could then be assigned to teammates")
    @PostMapping({"/skills/"})
    public ResponseEntity<SkillDTO> createSkill(@Valid @RequestBody SkillDTO skillToCreate) {

        SkillDTO newSkill = createSkillUseCase.execute(skillToCreate);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newSkill.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

}
