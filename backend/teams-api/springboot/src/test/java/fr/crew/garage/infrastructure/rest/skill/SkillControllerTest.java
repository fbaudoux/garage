package fr.crew.garage.infrastructure.rest.skill;

import fr.crew.garage.infrastructure.rest.team.SkillController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SkillControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SkillController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }


    @Test
    void createSkill() throws Exception {
        ResultActions location = this.mockMvc.perform(post("/skills/").contentType(MediaType.APPLICATION_JSON).content("{name:testSkill}")).andExpect(status().isCreated())
                .andExpect(header().exists("Location"));

        String headerValue = mvcResult.getResponse().getHeader("headerName");
    });//.andExpect(content().string(containsString("testTeam")));
}
}