package fr.crew.garage.domain.search;

import fr.crew.garage.domain.skill.entity.SkillEntity;
import fr.crew.garage.domain.skill.repository.SkillRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc

public class SearchDomainServiceTest {


    @Autowired
    SkillRepository skillRepository;

    @Autowired
    SearchDomainService searchDomainService;

    @Test
    @Transactional
    void searchOneCrewOfOneTeammate() {
        SkillEntity skill = skillRepository.findByName("use the force");
        Crew crew = new Crew("someone who can use the force", List.of(skill));
        searchDomainService.search(List.of(crew));
    }

    @Test
    @Transactional
    void searchOneCrewOfTwoTeammate() {
        SkillEntity skill1 = skillRepository.findByName("drive a x-wing");
        SkillEntity skill2 = skillRepository.findByName("use the force");
        Crew crew = new Crew("someone who can drive a x-wing", List.of(skill1, skill2));
        searchDomainService.search(List.of(crew));
    }


    @Test
    @Transactional
    void searchOneCrewOfOneTeammateWithNoSolution() {
        SkillEntity skill = skillRepository.findByName("do impossible things");
        Crew crew = new Crew("someone who can do impossible things", List.of(skill));
        searchDomainService.search(List.of(crew));
    }


}
