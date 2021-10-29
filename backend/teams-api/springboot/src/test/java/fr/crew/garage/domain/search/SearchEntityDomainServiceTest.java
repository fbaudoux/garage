package fr.crew.garage.domain.search;

import fr.crew.garage.domain.search.entity.CrewSearchEntity;
import fr.crew.garage.domain.skill.entity.SkillEntity;
import fr.crew.garage.domain.skill.repository.SkillRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
@AutoConfigureMockMvc

public class SearchEntityDomainServiceTest {


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

        Set<SkillEntity> lstSkill = new HashSet<>();
        lstSkill.add(skill);

        CrewSearchEntity crewSearch = new CrewSearchEntity("someone who can use the force", lstSkill);

        ArrayList<CrewSearchEntity> lstSearch = new ArrayList<>();
        lstSearch.add(crewSearch);

        searchDomainService.search(lstSearch);
    }

    @Test
    @Transactional
    void searchOneCrewOfTwoTeammate() {
        SkillEntity skill1 = skillRepository.findByName("drive a x-wing");
        SkillEntity skill2 = skillRepository.findByName("use the force");
        Set<SkillEntity> lstSkill = new HashSet<>();
        lstSkill.add(skill1);
        lstSkill.add(skill2);

        CrewSearchEntity crewSearch = new CrewSearchEntity("someone who can drive a x-wing", lstSkill);

        ArrayList<CrewSearchEntity> lstSearch = new ArrayList<>();
        lstSearch.add(crewSearch);
        searchDomainService.search(lstSearch);
    }


    @Test
    @Transactional
    void searchOneCrewOfOneTeammateWithNoSolution() {
        SkillEntity skill = skillRepository.findByName("do impossible things");

        Set<SkillEntity> lstSkill = new HashSet<>();
        lstSkill.add(skill);

        CrewSearchEntity crewSearchEntity = new CrewSearchEntity("someone who can do impossible things", lstSkill);

        ArrayList<CrewSearchEntity> lstSearch = new ArrayList<>();
        lstSearch.add(crewSearchEntity);

        searchDomainService.search(lstSearch);
    }


}
