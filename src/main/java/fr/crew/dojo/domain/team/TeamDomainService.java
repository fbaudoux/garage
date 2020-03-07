package fr.crew.dojo.domain.team;

import fr.crew.dojo.domain.team.entity.TeamEntity;
import fr.crew.dojo.domain.team.entity.TeammateEntity;
import fr.crew.dojo.domain.team.repository.TeamRepository;
import fr.crew.dojo.domain.team.repository.TeammateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class TeamDomainService {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    TeammateRepository teammateRepository;


    public Collection<TeamEntity> getAllTeams() {
        return teamRepository.getAllTeams();
    }

    public TeamEntity getTeam(Long teamId) {
        return teamRepository.get(teamId);
    }

    public Collection<TeammateEntity> getTeammatesForTeam(TeamEntity aTeam) {
        return teamRepository.getMembers(aTeam);
    }

    public TeamEntity createTeam(String name) {
        TeamEntity newTeam = new TeamEntity();
        newTeam.setName(name);
        teamRepository.save(newTeam);
        return newTeam;
    }

    public Collection<TeammateEntity> getAllTeammates() {
        return teammateRepository.getAllTeammates();
    }

    public TeammateEntity createTeammate(String name) {
        TeammateEntity newTeammate = new TeammateEntity();
        newTeammate.setName(name);
        teammateRepository.save(newTeammate);
        return newTeammate;
    }


    public void addTeammateToTeam(Long teammateId, Long teamId) {
        teamRepository.addTeammateToTeam(teammateId,teamId);
    }

    public void removeAll() {
    }
}
