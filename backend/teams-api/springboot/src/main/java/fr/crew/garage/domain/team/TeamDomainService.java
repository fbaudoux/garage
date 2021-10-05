package fr.crew.garage.domain.team;

import fr.crew.garage.api.team.TeammateDTO;
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
import java.util.Random;


@Service
public class TeamDomainService {


    Logger logger = LoggerFactory.getLogger(TeamDomainService.class);

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    TeammateRepository teammateRepository;

    @Autowired
    ModelMapper modelMapper;


    public Collection<TeamEntity> getAllTeams() {
        return teamRepository.findAll();
    }

    public TeamEntity getTeam(Long teamId) {
        return teamRepository.getOne(teamId);
    }

    public TeamEntity getTeamByName(String name) {
        return teamRepository.findByName(name);
    }

    public TeammateEntity getTeammateByName(String name) {
        return teammateRepository.findByName(name);
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

    public TeammateEntity createTeammate(TeammateDTO teammateDTO) {
        logger.info("A new teammate is created : " + teammateDTO.getName());
        TeammateEntity newTeammate = modelMapper.map(teammateDTO, TeammateEntity.class);
        teammateRepository.save(newTeammate);
        return newTeammate;
    }

    @Transactional
    public void addTeammateToTeam(TeammateEntity teammate, TeamEntity team) {
        logger.info("A new membership is created ");
        teamRepository.addTeammateToTeam(teammate.getId(), team.getId());
    }

    public void removeAll() {
    }

    public Page<TeamEntity> getAllTeamsPageByPage(Integer pageNumber) {
        return teamRepository.findAll(PageRequest.of(pageNumber, 10));
    }

    public TeamEntity createTeamWithRandomName() {

        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();


        return this.createTeam(generatedString);
    }
}
