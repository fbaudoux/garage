package fr.crew.garage.infrastructure.rest.team;


import fr.crew.garage.application.team.*;
import fr.crew.garage.application.team.command.AddTeammateToTeamRequest;
import fr.crew.garage.application.team.command.AddTeammateToTeamResponse;
import fr.crew.garage.application.team.command.CreateTeamRequest;
import fr.crew.garage.application.team.command.CreateTeamResponse;
import fr.crew.garage.application.team.command.CreateTeammateRequest;
import fr.crew.garage.application.team.command.CreateTeammateResponse;
import fr.crew.garage.application.team.command.CreateTeamsRequest;
import fr.crew.garage.application.team.command.CreateTeamsResponse;
import fr.crew.garage.application.team.command.GetAllTeammatesResponse;
import fr.crew.garage.application.team.command.GetAllTeamsPageByPageResponse;
import fr.crew.garage.application.team.command.GetAllTeamsResponse;
import fr.crew.garage.application.team.command.GetTeamRequest;
import fr.crew.garage.application.team.command.GetTeamResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.validation.Valid;
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
    CreateTeamsUseCase createTeamsUseCase;
    @Autowired
    CreateTeamUseCase createTeamUseCase;


    @ApiOperation(value = "getAllTeams", notes = "Get all teams without any details about membership")
    @GetMapping( {"/teams/"})
    public GetAllTeamsResponse getAllTeams() {
        return getAllTeamsUseCase.execute();
    }

    @ApiOperation(value = "getAllTeamsPageByPage", notes = "Get all teams without any details about membership")
    @GetMapping( {"/teams/page/{pageNumber}"})
    public GetAllTeamsPageByPageResponse getAllTeamsPageByPage(@PathVariable(value = "pageNumber") Integer pageNumber) {
        return getAllTeamsPageByPageUseCase.execute(pageNumber);
    }


    @ApiOperation(value = "getAllTeamsStream", notes = "Get all teams using Server Sent Event")
    @GetMapping( {"/teams/stream"})
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
    @GetMapping( {"/teams/{teamId}/"})
    public GetTeamResponse getTeam(@PathVariable(value = "teamId") Long teamId) {
        return getTeamUseCase.execute(new GetTeamRequest(teamId));
    }

    @ApiOperation(value = "getAllTeammates", notes = "Get all teammates")
    @GetMapping( {"/teammates/"})
    public GetAllTeammatesResponse getAllTeammates() {
        return getAllTeammatesUseCase.execute();
    }

    @ApiOperation(value = "createTeammate", notes = "Create a new teammates with a name given in parameter")
    @PostMapping( {"/teammates/"})
    public CreateTeammateResponse createTeammate(@Valid @RequestBody String name) {
        return createTeammateUseCase.execute(new CreateTeammateRequest(name));
    }

    @ApiOperation(value = "createTeam", notes = "Create a new team with a name given in parameter")
    @PostMapping( {"/teams/"})
    public CreateTeamResponse createTeam(@Valid @RequestBody String name) {
        return createTeamUseCase.execute(new CreateTeamRequest(name));
    }

    @ApiOperation(value = "addTeammateToTeam", notes = "Create a membership relation between a team and a teammate")
    @PostMapping( {"/teams/{teamId}/teammates/{teammateId}"})
    public AddTeammateToTeamResponse addTeammateToTeam(@PathVariable(value = "teamId") Long teamId, @PathVariable(value = "teammateId") Long teammateId) {
        return addTeammateToTeamUseCase.execute(new AddTeammateToTeamRequest(teammateId, teamId));
    }

    @ApiOperation(value = "createTeams", notes = "Create new temas with random names")
    @PostMapping(value = {"/teams/"}, params = "number")
    public CreateTeamsResponse createMultipleTeam(@RequestParam Integer number) {
        return createTeamsUseCase.execute(new CreateTeamsRequest(number));
    }


}
