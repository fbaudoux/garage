package fr.crew.garage.domain.team;

import fr.crew.garage.api.team.dto.TeamDTO;
import fr.crew.garage.domain.team.entity.TeamEntity;
import fr.crew.garage.domain.team.entity.TeammateEntity;
import fr.crew.garage.domain.team.repository.TeamRepository;
import fr.crew.garage.domain.team.repository.TeammateRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;


@Service
public class TeamDomainService {


    Logger logger = LoggerFactory.getLogger(TeamDomainService.class);

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    TeammateRepository teammateRepository;

    @Autowired
    ModelMapper modelMapper;


    public Collection<TeammateEntity> getTeammatesForTeam(TeamEntity aTeam) {
        return teammateRepository.findTeamMembers(aTeam.getId());
    }

    public TeamEntity createTeam(TeamEntity team) {
        logger.info("A new team is created : " +team.getName());
        return teamRepository.save(team);
    }

    public TeammateEntity createTeammate(TeammateEntity newTeammate) {
            logger.info("A new teammate is created : " + newTeammate.getName());
        return teammateRepository.save(newTeammate);
    }

    @Transactional
    public void addTeammateToTeam(TeammateEntity teammate, TeamEntity team) {
        logger.info("A new membership is created ");
        teamRepository.addTeammateToTeam(teammate.getId(), team.getId());
    }

    public Page<TeamEntity> getAllTeamsPageByPage(Integer pageNumber) {
        return teamRepository.findAll(PageRequest.of(pageNumber, 10));
    }
}
