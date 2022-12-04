package fr.crew.garage.in.rest;

import fr.crew.garage.api.skill.AddSkillToTeammateUseCase;
import fr.crew.garage.api.team.*;
import fr.crew.garage.domain.skill.SkillDomainService;
import fr.crew.garage.domain.skill.repository.SkillRepository;
import fr.crew.garage.domain.team.TeamDomainService;
import fr.crew.garage.domain.team.entity.TeamEntity;
import fr.crew.garage.domain.team.entity.TeammateEntity;
import fr.crew.garage.domain.team.repository.TeamRepository;
import fr.crew.garage.domain.team.repository.TeammateRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Controller team.
 */
@RestController
@Api(value = "Teams", tags = {"Teams"})
public class TeamController {

    final
    GetAllTeamsUseCase getAllTeamsUseCase;
    final
    GetAllTeamsPageByPageUseCase getAllTeamsPageByPageUseCase;
    final
    StreamAllTeamsUseCase streamAllTeamsUseCase;
    final
    GetAllTeammatesUseCase getAllTeammatesUseCase;
    final
    GetTeamUseCase getTeamUseCase;
    final
    AddTeammateToTeamUseCase addTeammateToTeamUseCase;
    final
    CreateTeammateUseCase createTeammateUseCase;
    final
    CreateTeamUseCase createTeamUseCase;
    final
    GetTeammateUseCase getTeammateUseCase;
    final
    AddSkillToTeammateUseCase addSkillToTeammateUseCase;
    final
    DeleteTeammateUseCase deleteTeammateUseCase;
    final
    DeleteTeamUseCase deleteTeamUseCase;

    public TeamController(TeamRepository teamRepository, TeammateRepository teammateRepository, SkillRepository skillRepository) {

        TeamDomainService teamDomainService = new TeamDomainService(teamRepository, teammateRepository);
        SkillDomainService skillDomainService = new SkillDomainService(skillRepository, teammateRepository);
        this.getAllTeamsPageByPageUseCase = new GetAllTeamsPageByPageUseCase(teamDomainService);
        this.getAllTeamsUseCase = new GetAllTeamsUseCase(teamRepository);
        this.streamAllTeamsUseCase = new StreamAllTeamsUseCase(teamRepository);
        this.getAllTeammatesUseCase = new GetAllTeammatesUseCase(teammateRepository);
        this.getTeamUseCase = new GetTeamUseCase(teamRepository);
        this.addTeammateToTeamUseCase = new AddTeammateToTeamUseCase(teamDomainService, teamRepository, teammateRepository);
        this.createTeammateUseCase = new CreateTeammateUseCase(teamDomainService);
        this.createTeamUseCase = new CreateTeamUseCase(teamDomainService);
        this.getTeammateUseCase = new GetTeammateUseCase(teammateRepository);
        this.addSkillToTeammateUseCase = new AddSkillToTeammateUseCase(skillDomainService, skillRepository, teammateRepository);
        this.deleteTeammateUseCase = new DeleteTeammateUseCase(teammateRepository);
        this.deleteTeamUseCase = new DeleteTeamUseCase(teamRepository);
    }


    @ApiOperation(value = "getAllTeams", notes = "Get all teams without any details about membership")
    @GetMapping({"/teams/"})
    public ResponseEntity<Collection<TeamEntity>> getAllTeams() {
        Collection<TeamEntity> res = getAllTeamsUseCase.execute();
        return ResponseEntity.ok(res);
    }

    @ApiOperation(value = "getAllTeamsPageByPage", notes = "Get all teams without any details about membership")
    @GetMapping({"/teams/page/{pageNumber}"})
    public ResponseEntity<Page<TeamEntity>> getAllTeamsPageByPage(@PathVariable(value = "pageNumber") Integer pageNumber) {
        Page<TeamEntity> res = null;
        return ResponseEntity.ok(res);
    }


    @ApiOperation(value = "getAllTeamsStream", notes = "Get all teams using Server Sent Event")
    @GetMapping({"/teams/stream"})
    public ResponseBodyEmitter getAllTeamsStream() {
        final SseEmitter emitter = new SseEmitter();
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(() -> {
            try {
                //streamAllTeamsUseCase.execute(emitter);
            } catch (Exception e) {
                e.printStackTrace();
                emitter.completeWithError(e);
                return;
            }

            emitter.complete();
        });

        return emitter;
    }


    @ApiOperation(value = "getTeam", notes = "Get a team with its teammates")
    @GetMapping({"/teams/{teamId}/"})
    public ResponseEntity<TeamEntity> getTeam(@PathVariable(value = "teamId") Long teamId) {
        return ResponseEntity.ok(getTeamUseCase.execute(teamId));
    }

    @ApiOperation(value = "getAllTeammates", notes = "Get all teammates")
    @GetMapping({"/teammates/"})
    public ResponseEntity<Collection<TeammateEntity>> getAllTeammates() {

        Collection<TeammateEntity> res = getAllTeammatesUseCase.execute();
        return ResponseEntity.ok(res);
    }

    @ApiOperation(value = "getTeammate", notes = "Get a teammate with its skills")
    @GetMapping({"/teammates/{teammateId}/"})
    public ResponseEntity<TeammateEntity> getTeammate(@PathVariable(value = "teammateId") Long teammateId) {
        return ResponseEntity.ok(getTeammateUseCase.execute(teammateId));
    }

    @ApiOperation(value = "createTeammate", notes = "Create a new teammates with a name given in parameter")
    @PostMapping({"/teammates/"})
    public ResponseEntity createTeammate(@Valid @RequestBody String name) {

        TeammateEntity newMate = new TeammateEntity();
        newMate.setName(name);
        TeammateEntity res = createTeammateUseCase.execute(newMate);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(res.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "createTeam", notes = "Create a new team with a name given in parameter")
    @PostMapping({"/teams/"})
    public ResponseEntity createTeam(@Valid @RequestBody TeamEntity teamDTO) {
        TeamEntity res = createTeamUseCase.execute(teamDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(res.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "addTeammateToTeam", notes = "Create a membership relation between a team and a teammate")
    @PostMapping({"/teams/{teamId}/teammates/{teammateId}"})
    public ResponseEntity addTeammateToTeam(@PathVariable(value = "teamId") Long teamId, @PathVariable(value = "teammateId") Long teammateId) {
        TeammateEntity mate = new TeammateEntity();
        mate.setId(teammateId);

        TeamEntity team = new TeamEntity();
        team.setId(teamId);

        addTeammateToTeamUseCase.execute(mate, team);
        return ResponseEntity.ok().build();
    }

    @PutMapping({"/teammates/"})
    public ResponseEntity<TeammateEntity> updateTeammate(@Valid @RequestBody TeammateEntity teammateDTO) {
        return ResponseEntity.ok(createTeammateUseCase.execute(teammateDTO));
    }

    @PutMapping({"/teams/"})
    public ResponseEntity<TeamEntity> updateTeam(@Valid @RequestBody TeamEntity teamDTO) {
        return ResponseEntity.ok(createTeamUseCase.execute(teamDTO));
    }

    @DeleteMapping({"/teammates/{teammateId}"})
    public ResponseEntity deleteTeammate(@PathVariable(value = "teammateId") Long teammateId) {
        TeammateEntity dto = new TeammateEntity();
        dto.setId(teammateId);
        deleteTeammateUseCase.execute(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping({"/teams/{teamId}"})
    public ResponseEntity deleteTeam(@PathVariable(value = "teamId") Long teamId) {
        TeamEntity dto = new TeamEntity();
        dto.setId(teamId);
        deleteTeamUseCase.execute(dto);
        return ResponseEntity.ok().build();
    }


}
