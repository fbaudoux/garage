package fr.crew.dojo.application.team;

import fr.crew.dojo.application.team.command.AddTeammateToTeamRequest;
import fr.crew.dojo.application.team.command.AddTeammateToTeamResponse;
import fr.crew.dojo.application.team.command.CreateTeamRequest;
import fr.crew.dojo.application.team.command.CreateTeamResponse;
import fr.crew.dojo.application.team.command.CreateTeammateRequest;
import fr.crew.dojo.application.team.command.CreateTeammateResponse;
import fr.crew.dojo.application.team.command.GetAllTeammatesResponse;
import fr.crew.dojo.application.team.command.GetAllTeamsResponse;
import fr.crew.dojo.application.team.command.GetTeamRequest;
import fr.crew.dojo.application.team.command.GetTeamResponse;
import fr.crew.dojo.domain.team.TeamDomainService;
import fr.crew.dojo.domain.team.entity.TeamEntity;
import fr.crew.dojo.domain.team.entity.TeammateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class TeamApplicationService {


    @Autowired
    TeamDomainService domainService;


    public GetAllTeamsResponse getAllTeams() {
        return new GetAllTeamsResponse(domainService.getAllTeams());
    }

    public GetTeamResponse getTeam(GetTeamRequest req) {

        TeamEntity aTeam =  domainService.getTeam(req.getTeamId());
        Collection<TeammateEntity> members = domainService.getTeammatesForTeam(aTeam);
        return new GetTeamResponse(aTeam, members);
    }

    public CreateTeamResponse createTeam(CreateTeamRequest req) {

        return new CreateTeamResponse(domainService.createTeam(req.getName()));
    }

    public GetAllTeammatesResponse getAllTeammates() {
        return new GetAllTeammatesResponse(domainService.getAllTeammates());
    }

    public CreateTeammateResponse createTeammate(CreateTeammateRequest req) {

        return new CreateTeammateResponse(domainService.createTeammate(req.getName()));

    }

    public AddTeammateToTeamResponse addTeammateToTeam(AddTeammateToTeamRequest req) {

        return new AddTeammateToTeamResponse(domainService.addTeammateToTeam(req.getTeammateId(),req.getTeamId()));
    }

}
