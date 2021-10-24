package fr.crew.garage.in.rest;

import fr.crew.garage.api.skill.CreateSkillUseCase;
import fr.crew.garage.api.skill.GetAllSkillsUseCase;
import fr.crew.garage.api.skill.dto.SkillDTO;
import fr.crew.garage.api.team.dto.TeammateDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;

@RestController
@Api(value = "Skills", tags = {"Skills"})
public class SkillController {


    @Autowired
    CreateSkillUseCase createSkillUseCase;

    @Autowired
    GetAllSkillsUseCase getAllSkillsUseCase;

    @ApiOperation(value = "createSkill", notes = "Create a new skill with a name given in parameter, this skill could then be assigned to teammates")
    @PostMapping({"/skills/"})
    public ResponseEntity<SkillDTO> createSkill(@Valid @RequestBody SkillDTO skillToCreate) {

        SkillDTO newSkill = createSkillUseCase.execute(skillToCreate);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newSkill.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @GetMapping({"/skills/"})
    public ResponseEntity<Collection<SkillDTO>> getAllSkills() {

        Collection<SkillDTO> res = getAllSkillsUseCase.execute();
        return ResponseEntity.ok(res);
    }

}
