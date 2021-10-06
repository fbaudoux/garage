package fr.crew.garage.domain.skill;

import fr.crew.garage.domain.skill.entity.SkillEntity;
import fr.crew.garage.domain.skill.repository.SkillRepository;
import fr.crew.garage.domain.team.entity.TeammateEntity;
import fr.crew.garage.domain.team.repository.TeammateRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SkillDomainServiceTest {

    @Autowired
    TeammateRepository teammateRepository;

    @Autowired
    SkillDomainService domainService;

    @Autowired
    SkillRepository skillRepository;

    @Test
    @Order(1)
    void createSkill() {
        SkillEntity kungfu = domainService.createSkill("kung-fu");
        assertEquals(kungfu.getName(), "kung-fu");

    }

    @Test
    @Order(2)
    @Transactional
    void getAllSkills() {
        assertTrue(!skillRepository.findAll().isEmpty());
        assertNotNull(skillRepository.findByName("kung-fu"));
    }


    @Test
    @Order(3)
    @Transactional
    @Rollback(false)
    void addSkillToTeammate() {

        SkillEntity askill = skillRepository.findByName("drive a x-wing");
        TeammateEntity yoda = teammateRepository.findByName("Yoda");
        domainService.addSkillToTeammate(askill, yoda);
        assertEquals(yoda.getSkills().contains(askill), true);
    }


    @Test
    @Order(4)
    @Transactional
    void removeSkillToTeammate() {
        SkillEntity askill = skillRepository.findByName("drive a x-wing");
        TeammateEntity yoda = teammateRepository.findByName("Yoda");
        assertEquals(true, yoda.getSkills().contains(askill));
        domainService.removeSkillToTeammate(askill, yoda);
        assertEquals(false, yoda.getSkills().contains(askill));
    }
}
