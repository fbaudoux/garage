package fr.crew.dojo.infrastructure.rest.team;

import fr.crew.dojo.domain.team.TeamDomainService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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


    @BeforeEach
    void setUp() {
        domainService.createTeammate("foo");
        domainService.createTeammate("bar");
        domainService.createTeam("foo_team");
        domainService.createTeam("bar_team");
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
        this.mockMvc.perform(get("/teams/1/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("foo_team")));
    }

    @Test
    void getAllTeammates() throws Exception  {
        this.mockMvc.perform(get("/teammates/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("bar")));
    }

    @Test
    void createTeammate() throws Exception  {
        this.mockMvc.perform(post("/teammates/").contentType(MediaType.APPLICATION_JSON).content("{name:testTeammate}")).andExpect(status().isOk()).andExpect(content().string(containsString("testTeammate")));
    }

    @Test
    void createTeam() throws Exception  {
        this.mockMvc.perform(post("/teams/").contentType(MediaType.APPLICATION_JSON).content("{name:testTeam}")).andExpect(status().isOk()).andExpect(content().string(containsString("testTeam")));
    }

    @Test
    void addTeammateToTeam() throws Exception {
        this.mockMvc.perform(post("/teams/1/teammates/1")).andExpect(status().isOk()).andExpect(content().string(containsString("\"numberOfMembers\":1")));
    }
}