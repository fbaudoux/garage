package fr.crew.garage.domain.search;

import fr.crew.garage.domain.skill.entity.SkillEntity;
import fr.crew.garage.domain.skill.repository.SkillRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;

@SpringBootTest
@AutoConfigureMockMvc

public class SearchDomainServiceTest {


    @Autowired
    SkillRepository skillRepository;

    @Autowired
    SearchDomainService searchDomainService;

    @Autowired
    ModelMapper modelMapper;

    @Test
    @Transactional
    void searchOneCrewOfOneTeammate() {
        SkillEntity skill = skillRepository.findByName("use the force");

        ArrayList<SkillEntity> lstSkill = new ArrayList<>();
        lstSkill.add(skill);

        CrewSearch crewSearch = new CrewSearch("someone who can use the force", lstSkill);

        ArrayList<CrewSearch> lstSearch = new ArrayList<>();
        lstSearch.add(crewSearch);

        searchDomainService.search(lstSearch);
    }

    @Test
    @Transactional
    void searchOneCrewOfTwoTeammate() {
        SkillEntity skill1 = skillRepository.findByName("drive a x-wing");
        SkillEntity skill2 = skillRepository.findByName("use the force");
        ArrayList<SkillEntity> lstSkill = new ArrayList<>();
        lstSkill.add(skill1);
        lstSkill.add(skill2);

        CrewSearch crewSearch = new CrewSearch("someone who can drive a x-wing", lstSkill);

        ArrayList<CrewSearch> lstSearch = new ArrayList<>();
        lstSearch.add(crewSearch);
        searchDomainService.search(lstSearch);
    }


    @Test
    @Transactional
    void searchOneCrewOfOneTeammateWithNoSolution() {
        SkillEntity skill = skillRepository.findByName("do impossible things");

        ArrayList<SkillEntity> lstSkill = new ArrayList<>();
        lstSkill.add(skill);

        CrewSearch crewSearch = new CrewSearch("someone who can do impossible things", lstSkill);

        ArrayList<CrewSearch> lstSearch = new ArrayList<>();
        lstSearch.add(crewSearch);

        searchDomainService.search(lstSearch);
    }


}
