package fr.crew.garage.in.rest;

import fr.crew.garage.api.skill.CreateSkillUseCase;
import fr.crew.garage.api.skill.DeleteSkillUseCase;
import fr.crew.garage.api.skill.GetAllSkillsUseCase;
import fr.crew.garage.api.skill.GetSkillUseCase;
import fr.crew.garage.api.skill.dto.SkillDTO;
import fr.crew.garage.api.team.dto.TeammateDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;

@RestController
@Api(value = "Skills", tags = {"Skills"})
public class SkillController {


    final
    CreateSkillUseCase createSkillUseCase;

    final
    GetAllSkillsUseCase getAllSkillsUseCase;

    final
    GetSkillUseCase getSkillUseCase;

    final
    DeleteSkillUseCase deleteSkillUseCase;

    public SkillController(CreateSkillUseCase createSkillUseCase, GetAllSkillsUseCase getAllSkillsUseCase, GetSkillUseCase getSkillUseCase, DeleteSkillUseCase deleteSkillUseCase) {
        this.createSkillUseCase = createSkillUseCase;
        this.getAllSkillsUseCase = getAllSkillsUseCase;
        this.getSkillUseCase = getSkillUseCase;
        this.deleteSkillUseCase = deleteSkillUseCase;
    }

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

    @GetMapping({"/skills/{id}"})
    public ResponseEntity<SkillDTO> getSkill(@PathVariable Long id) {
        return ResponseEntity.ok(getSkillUseCase.execute(id));
    }

    @DeleteMapping({"/skills/{id}"})
    public ResponseEntity deleteSkill(@PathVariable Long id) {
        SkillDTO dto = new SkillDTO();
        dto.setId(id);
        deleteSkillUseCase.execute(dto);
        return ResponseEntity.ok().build();
    }

}
