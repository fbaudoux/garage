package fr.crew.dojo.domain.team;

import fr.crew.dojo.domain.team.entity.TeamEntity;
import fr.crew.dojo.domain.team.entity.TeammateEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

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
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void getAllTeams() {
        assertEquals(3,domainService.getAllTeams().size());
    }

    @Test
    void getTeam() {
        assertEquals(domainService.getTeam(1L).getName(),"A");
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
        assertEquals(3,domainService.getAllTeammates().size());
    }

    @Test
    void createTeammate() {
        TeammateEntity guy = domainService.createTeammate("guy");
        assertEquals(guy.getName(),"guy");
    }

    @Test
    void addTeammateToTeam() {
        domainService.addTeammateToTeam(1L, 2L);
        TeamEntity team = new TeamEntity();
        team.setId(2L);
        Collection<TeammateEntity> teammatesForTeam = domainService.getTeammatesForTeam(team);
        assertEquals(1,teammatesForTeam.size());
    }
}