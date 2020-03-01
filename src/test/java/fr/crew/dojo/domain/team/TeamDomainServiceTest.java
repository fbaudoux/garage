package fr.crew.dojo.domain.team;

import fr.crew.dojo.domain.team.entity.TeamEntity;
import fr.crew.dojo.domain.team.entity.TeammateEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class TeamDomainServiceTest {

    @Autowired
    TeamDomainService domainService;

    TeamEntity teamFoo;
    TeammateEntity teammateFoo;

    @BeforeEach
    void setUp() {
        teamFoo = domainService.createTeam("foo team");
        teammateFoo = domainService.createTeammate("foo");
    }

    @AfterEach
    void tearDown() {
        domainService.removeAll();
    }

    @Test
    void getAllTeams() {
         assertTrue(domainService.getAllTeams().contains(teamFoo));
    }

    @Test
    void getTeam() {
        assertEquals(domainService.getTeam(1L),teamFoo);
    }

    @Test
    void getTeammatesForTeam() {
    }

    @Test
    void createTeam() {
        TeamEntity t  = domainService.createTeam("bar team");
        assertEquals(t.getName(),"bar team");
    }

    @Test
    void getAllTeammates() {
        assertTrue(domainService.getAllTeammates().contains(teammateFoo));
    }

    @Test
    void createTeammate() {
        TeammateEntity guy = domainService.createTeammate("guy");
        assertEquals(guy.getName(),"guy");
    }

    @Test
    void addTeammateToTeam() {
        int nb = domainService.addTeammateToTeam(1L, 1L);
        assertEquals(nb,1);
    }
}