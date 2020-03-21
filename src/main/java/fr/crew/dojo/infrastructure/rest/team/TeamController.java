package fr.crew.dojo.infrastructure.rest.team;


import fr.crew.dojo.application.team.TeamApplicationService;
import fr.crew.dojo.application.team.command.AddTeammateToTeamRequest;
import fr.crew.dojo.application.team.command.AddTeammateToTeamResponse;
import fr.crew.dojo.application.team.command.CreateTeamRequest;
import fr.crew.dojo.application.team.command.CreateTeamResponse;
import fr.crew.dojo.application.team.command.CreateTeammateRequest;
import fr.crew.dojo.application.team.command.CreateTeammateResponse;
import fr.crew.dojo.application.team.command.GetAllTeammatesResponse;
import fr.crew.dojo.application.team.command.GetAllTeamsPageByPageResponse;
import fr.crew.dojo.application.team.command.GetAllTeamsResponse;
import fr.crew.dojo.application.team.command.GetTeamRequest;
import fr.crew.dojo.application.team.command.GetTeamResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Controller team.
 */
@RestController
@Api(value = "Teams", description = "Teams endpoints", tags = {"Teams"})
public class TeamController {

    @Autowired
    TeamApplicationService applicationService;

    @ApiOperation(value = "getAllTeams", notes = "Get all teams without any details about membership")
    @GetMapping( {"/teams/"})
    public GetAllTeamsResponse getAllTeams() {
        return applicationService.getAllTeams();
    }

    @ApiOperation(value = "getAllTeamsPageByPage", notes = "Get all teams without any details about membership")
    @GetMapping( {"/teams/page/{pageNumber}"})
    public GetAllTeamsPageByPageResponse getAllTeamsPageByPage(@PathVariable(value = "pageNumber") Integer pageNumber) {
        return applicationService.getAllTeamsPageByPage(pageNumber);
    }

    @ApiOperation(value = "getTeam", notes = "Get a team with its teammates")
    @GetMapping( {"/teams/{teamId}/"})
    public GetTeamResponse getTeam(@PathVariable(value = "teamId") Long teamId) {
        return applicationService.getTeam(new GetTeamRequest(teamId));
    }

    @ApiOperation(value = "getAllTeammates", notes = "Get all teammates")
    @GetMapping( {"/teammates/"})
    public GetAllTeammatesResponse getAllTeammates() {
        return applicationService.getAllTeammates();
    }

    @ApiOperation(value = "createTeammate", notes = "Create a new teammates with a name given in parameter")
    @PostMapping( {"/teammates/"})
    public CreateTeammateResponse createTeammate(@Valid @RequestBody String name) {
        return applicationService.createTeammate(new CreateTeammateRequest(name));
    }

    @ApiOperation(value = "createTeam", notes = "Create a new team with a name given in parameter")
    @PostMapping( {"/teams/"})
    public CreateTeamResponse createTeam(@Valid @RequestBody String name) {
        return applicationService.createTeam(new CreateTeamRequest(name));
    }

    @ApiOperation(value = "addTeammateToTeam", notes = "Create a membership relation between a team and a teammate")
    @PostMapping( {"/teams/{teamId}/teammates/{teammateId}"})
    public AddTeammateToTeamResponse addTeammateToTeam(@PathVariable(value = "teamId") Long teamId, @PathVariable(value = "teammateId") Long teammateId) {
        return applicationService.addTeammateToTeam(new AddTeammateToTeamRequest(teammateId, teamId));
    }




}
