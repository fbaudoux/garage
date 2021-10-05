package fr.crew.garage.in.rest;

import fr.crew.garage.api.team.AddTeammateToTeamUseCase;
import fr.crew.garage.api.team.CreateTeamUseCase;
import fr.crew.garage.api.team.CreateTeammateUseCase;
import fr.crew.garage.api.team.GetAllTeammatesUseCase;
import fr.crew.garage.api.team.GetAllTeamsPageByPageUseCase;
import fr.crew.garage.api.team.GetAllTeamsUseCase;
import fr.crew.garage.api.team.GetTeamUseCase;
import fr.crew.garage.api.team.StreamAllTeamsUseCase;
import fr.crew.garage.api.team.dto.TeamDTO;
import fr.crew.garage.api.team.dto.TeammateDTO;
import fr.crew.garage.domain.team.entity.TeamEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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

    @Autowired
    GetAllTeamsUseCase getAllTeamsUseCase;
    @Autowired
    GetAllTeamsPageByPageUseCase getAllTeamsPageByPageUseCase;
    @Autowired
    StreamAllTeamsUseCase streamAllTeamsUseCase;
    @Autowired
    GetAllTeammatesUseCase getAllTeammatesUseCase;
    @Autowired
    GetTeamUseCase getTeamUseCase;
    @Autowired
    AddTeammateToTeamUseCase addTeammateToTeamUseCase;
    @Autowired
    CreateTeammateUseCase createTeammateUseCase;
    @Autowired
    CreateTeamUseCase createTeamUseCase;


    @ApiOperation(value = "getAllTeams", notes = "Get all teams without any details about membership")
    @GetMapping({"/teams/"})
    public ResponseEntity<Collection<TeamDTO>> getAllTeams() {
        Collection<TeamDTO> res = getAllTeamsUseCase.execute();
        return ResponseEntity.ok(res);
    }

    @ApiOperation(value = "getAllTeamsPageByPage", notes = "Get all teams without any details about membership")
    @GetMapping({"/teams/page/{pageNumber}"})
    public ResponseEntity<Page<TeamEntity>> getAllTeamsPageByPage(@PathVariable(value = "pageNumber") Integer pageNumber) {
        Page<TeamEntity> res = getAllTeamsPageByPageUseCase.execute(pageNumber);
        return ResponseEntity.ok(res);
    }


    @ApiOperation(value = "getAllTeamsStream", notes = "Get all teams using Server Sent Event")
    @GetMapping({"/teams/stream"})
    public ResponseBodyEmitter getAllTeamsStream() {
        final SseEmitter emitter = new SseEmitter();
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(() -> {
            try {
                streamAllTeamsUseCase.execute(emitter);
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
    public ResponseEntity<TeamDTO> getTeam(@PathVariable(value = "teamId") Long teamId) {
        return ResponseEntity.ok(getTeamUseCase.execute(teamId));
    }

    @ApiOperation(value = "getAllTeammates", notes = "Get all teammates")
    @GetMapping({"/teammates/"})
    public ResponseEntity<Collection<TeammateDTO>> getAllTeammates() {

        Collection<TeammateDTO> res = getAllTeammatesUseCase.execute();
        return ResponseEntity.ok(res);
    }

    @ApiOperation(value = "createTeammate", notes = "Create a new teammates with a name given in parameter")
    @PostMapping({"/teammates/"})
    public ResponseEntity createTeammate(@Valid @RequestBody String name) {

        TeammateDTO newMate = new TeammateDTO();
        newMate.setName(name);
        TeammateDTO res = createTeammateUseCase.execute(newMate);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(res.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "createTeam", notes = "Create a new team with a name given in parameter")
    @PostMapping({"/teams/"})
    public ResponseEntity createTeam(@Valid @RequestBody String name) {
        TeamDTO res = createTeamUseCase.execute(name);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(res.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "addTeammateToTeam", notes = "Create a membership relation between a team and a teammate")
    @PostMapping({"/teams/{teamId}/teammates/{teammateId}"})
    public ResponseEntity addTeammateToTeam(@PathVariable(value = "teamId") Long teamId, @PathVariable(value = "teammateId") Long teammateId) {
        TeammateDTO mate = new TeammateDTO();
        mate.setId(teammateId);

        TeamDTO team = new TeamDTO();
        team.setId(teamId);

        addTeammateToTeamUseCase.execute(mate, team);
        return ResponseEntity.ok().build();
    }

}
