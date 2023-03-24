package fr.crew.garage.in.rest;

import fr.crew.garage.api.skill.CreateSkillUseCase;
import fr.crew.garage.api.skill.DeleteSkillUseCase;
import fr.crew.garage.api.skill.GetAllSkillsUseCase;
import fr.crew.garage.api.skill.GetSkillUseCase;
import fr.crew.garage.domain.skill.SkillDomainService;
import fr.crew.garage.domain.skill.entity.SkillEntity;
import fr.crew.garage.domain.skill.repository.SkillRepository;
import fr.crew.garage.domain.team.repository.TeammateRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
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

    public SkillController(SkillRepository skillRepository, TeammateRepository teammateRepository) {

        SkillDomainService skillDomainService = new SkillDomainService(skillRepository, teammateRepository);

        this.createSkillUseCase = new CreateSkillUseCase(skillDomainService);
        this.getAllSkillsUseCase = new GetAllSkillsUseCase(skillRepository);
        this.getSkillUseCase = new GetSkillUseCase(skillRepository);
        this.deleteSkillUseCase = new DeleteSkillUseCase(skillRepository);
    }

    @ApiOperation(value = "createSkill", notes = "Create a new skill with a name given in parameter, this skill could then be assigned to teammates")
    @PostMapping({"/skills/"})
    public ResponseEntity<SkillEntity> createSkill(@Valid @RequestBody SkillEntity skillToCreate) {

        SkillEntity newSkill = createSkillUseCase.execute(skillToCreate);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newSkill.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @GetMapping({"/skills/"})
    public ResponseEntity<Collection<SkillEntity>> getAllSkills() {

        Collection<SkillEntity> res = getAllSkillsUseCase.execute();
        return ResponseEntity.ok(res);
    }

    @GetMapping({"/skills/{id}"})
    public ResponseEntity<SkillEntity> getSkill(@PathVariable Long id) {
        return ResponseEntity.ok(getSkillUseCase.execute(id));
    }

    @DeleteMapping({"/skills/{id}"})
    public ResponseEntity deleteSkill(@PathVariable Long id) {
        SkillEntity dto = new SkillEntity();
        dto.setId(id);
        deleteSkillUseCase.execute(dto);
        return ResponseEntity.ok().build();
    }

}
