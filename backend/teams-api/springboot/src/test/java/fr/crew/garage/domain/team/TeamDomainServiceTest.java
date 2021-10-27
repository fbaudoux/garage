package fr.crew.garage.domain.team;

import fr.crew.garage.api.team.dto.TeamDTO;
import fr.crew.garage.domain.team.entity.TeamEntity;
import fr.crew.garage.domain.team.entity.TeammateEntity;
import fr.crew.garage.domain.team.repository.TeamRepository;
import fr.crew.garage.domain.team.repository.TeammateRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class TeamDomainServiceTest {

    @Autowired
    TeamDomainService domainService;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    TeammateRepository teammateRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    @Transactional
    void getAllTeams() {
        assertEquals(2, teamRepository.findAll().size());
    }

    @Test
    @Transactional
    void getTeam() {
        assertEquals(teamRepository.getById(1L).getName(), "Marvel");
    }

    @Test
    @Transactional
    void getTeammatesForTeam() {
        TeamEntity team = teamRepository.findByName("Marvel");
        assertEquals(4, domainService.getTeammatesForTeam(team).size());
    }

    @Test
    @Transactional
    void createTeam() {
        TeamEntity teamDTO = new TeamEntity();
        teamDTO.setName("bar team");
        TeamEntity t = domainService.createTeam(teamDTO);
        assertEquals(t.getName(), "bar team");
    }

    @Test
    @Transactional
    void createTeammate() {
        TeammateEntity entity = new TeammateEntity();
        entity.setName("guy");
        TeammateEntity guy = domainService.createTeammate(entity);
        assertEquals(guy.getName(), "guy");
    }

    @Test
    @Transactional
    void addTeammateToTeam() {
        TeammateEntity entity = new TeammateEntity();
        entity.setName("John");
        TeammateEntity john = domainService.createTeammate(entity);
        TeamEntity team = domainService.teamRepository.findByName("Marvel");
        domainService.addTeammateToTeam(john, team);
        Collection<TeammateEntity> teammatesForTeam = domainService.getTeammatesForTeam(team);
        assertEquals(5, teammatesForTeam.size());
    }
}