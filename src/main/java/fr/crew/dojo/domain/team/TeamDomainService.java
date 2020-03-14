package fr.crew.dojo.domain.team;

import fr.crew.dojo.domain.team.entity.TeamEntity;
import fr.crew.dojo.domain.team.entity.TeammateEntity;
import fr.crew.dojo.domain.team.repository.TeamRepository;
import fr.crew.dojo.domain.team.repository.TeammateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class TeamDomainService {


    Logger logger = LoggerFactory.getLogger(TeamDomainService.class);

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    TeammateRepository teammateRepository;


    public Collection<TeamEntity> getAllTeams() {
        return teamRepository.findAll();
    }

    public TeamEntity getTeam(Long teamId) {
        return teamRepository.getOne(teamId);
    }

    public Collection<TeammateEntity> getTeammatesForTeam(TeamEntity aTeam) {
        return teammateRepository.findTeamMembers(aTeam.getId());
    }

    public TeamEntity createTeam(String name) {
        logger.info("A new team is created : " + name);
        TeamEntity newTeam = new TeamEntity();
        newTeam.setName(name);
        teamRepository.save(newTeam);
        return newTeam;
    }

    public Collection<TeammateEntity> getAllTeammates() {
        return teammateRepository.findAll();
    }

    public TeammateEntity createTeammate(String name) {
        logger.info("A new teammate is created : " + name);
        TeammateEntity newTeammate = new TeammateEntity();
        newTeammate.setName(name);
        teammateRepository.save(newTeammate);
        return newTeammate;
    }


    public void addTeammateToTeam(Long teammateId, Long teamId) {
        logger.info("A new membership is created ");
        teamRepository.addTeammateToTeam(teammateId,teamId);
    }

    public void removeAll() {
    }
}
