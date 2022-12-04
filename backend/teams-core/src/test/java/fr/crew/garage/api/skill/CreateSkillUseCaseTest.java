package fr.crew.garage.api.skill;

import fr.crew.garage.api.WithMockRepositoryTest;
import fr.crew.garage.domain.skill.SkillDomainService;
import fr.crew.garage.domain.skill.entity.SkillEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateSkillUseCaseTest extends WithMockRepositoryTest {
    @Test
    void execute() {

        CreateSkillUseCase useCase = new CreateSkillUseCase(new SkillDomainService(this.skillRepository, this.teammateRepository));
        SkillEntity newSkill = new SkillEntity();
        newSkill.setName("new skill");
        newSkill = useCase.execute(newSkill);
        assertEquals("new skill", newSkill.getName());


    }
}