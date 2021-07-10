package fr.crew.garage.domain.team;

import fr.crew.garage.domain.team.entity.TeamEntity;
import fr.crew.garage.domain.team.entity.TeammateEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
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
    @Transactional
    void getAllTeams() {
        assertEquals(3,domainService.getAllTeams().size());
    }

    @Test
    @Transactional
    void getTeam() {
        assertEquals(domainService.getTeam(1L).getName(),"A");
    }

    @Test
    @Transactional
    void getTeammatesForTeam() {
        TeamEntity team = new TeamEntity();
        team.setId(1L);

        assertEquals(3,domainService.getTeammatesForTeam(team).size());
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
        assertEquals(3,domainService.getAllTeammates().size());
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
        domainService.addTeammateToTeam(1L, 2L);
        TeamEntity team = new TeamEntity();
        team.setId(2L);
        Collection<TeammateEntity> teammatesForTeam = domainService.getTeammatesForTeam(team);
        assertEquals(1,teammatesForTeam.size());
    }
}