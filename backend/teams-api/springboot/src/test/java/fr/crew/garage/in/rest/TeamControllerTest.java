package fr.crew.garage.in.rest;

import fr.crew.garage.domain.team.TeamDomainService;
import fr.crew.garage.domain.team.entity.TeamEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TeamDomainService domainService;


    private TeamEntity foo_team = null;
    private TeamEntity bar_team = null;


    @BeforeEach
    void setUp() {

        foo_team = domainService.createTeam("foo_team");
        bar_team = domainService.createTeam("bar_team");
    }

    @AfterEach
    void tearDown() {
        domainService.removeAll();
    }

    @Test
    void getAllTeams() throws Exception {

        this.mockMvc.perform(get("/teams/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("bar_team")));

    }

    @Test
    void getTeam() throws Exception {
        this.mockMvc.perform(get("/teams/" + foo_team.getId() + "/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("foo_team")));
    }

    @Test
    void getAllTeammates() throws Exception {
        this.mockMvc.perform(get("/teammates/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("bar")));
    }

    @Test
    void createTeammate() throws Exception {
        this.mockMvc.perform(post("/teammates/").contentType(MediaType.APPLICATION_JSON).content("{name:testTeammate}")).andExpect(status().isCreated());
    }

    @Test
    void createTeam() throws Exception {
        this.mockMvc.perform(post("/teams/").contentType(MediaType.APPLICATION_JSON).content("{name:testTeam}")).andExpect(status().isCreated());
    }

    @Test
    @Transactional
    void addTeammateToTeam() throws Exception {
        this.mockMvc.perform(post("/teams/" + foo_team.getId() + "/teammates/1")).andExpect(status().isOk());
        this.mockMvc.perform(get("/teams/" + foo_team.getId() + "/")).andExpect(status().isOk()).andExpect(content().string(containsString("\"name\":\"foo_team\"")));
    }
}