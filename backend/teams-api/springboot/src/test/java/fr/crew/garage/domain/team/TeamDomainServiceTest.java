package fr.crew.garage.domain.team;

import fr.crew.garage.domain.team.entity.TeamEntity;
import fr.crew.garage.domain.team.entity.TeammateEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class TeamDomainServiceTest {

    @Autowired
    TeamDomainService domainService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    @Transactional
    void getAllTeams() {
        assertEquals(2,domainService.getAllTeams().size());
    }

    @Test
    @Transactional
    void getTeam() {
        assertEquals(domainService.getTeam(1L).getName(),"Marvel");
    }

    @Test
    @Transactional
    void getTeammatesForTeam() {
        TeamEntity team = domainService.getTeamByName("Marvel");

        assertEquals(4,domainService.getTeammatesForTeam(team).size());
    }

    @Test
    @Transactional
    void createTeam() {
        TeamEntity t  = domainService.createTeam("bar team");
        assertEquals(t.getName(),"bar team");
    }

    @Test
    @Transactional
    void getAllTeammates() {
        assertEquals(8,domainService.getAllTeammates().size());
    }

    @Test
    @Transactional
    void createTeammate() {
        TeammateEntity guy = domainService.createTeammate("guy");
        assertEquals(guy.getName(),"guy");
    }

    @Test
    @Transactional
    void addTeammateToTeam() {
        TeammateEntity john = domainService.createTeammate("John");
        TeamEntity team = domainService.teamRepository.findByName("Marvel");
        domainService.addTeammateToTeam(john, team);
        Collection<TeammateEntity> teammatesForTeam = domainService.getTeammatesForTeam(team);
        assertEquals(5,teammatesForTeam.size());
    }
}