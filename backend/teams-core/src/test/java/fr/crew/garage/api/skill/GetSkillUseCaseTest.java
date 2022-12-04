package fr.crew.garage.api.skill;

import fr.crew.garage.api.WithMockRepositoryTest;
import fr.crew.garage.domain.skill.entity.SkillEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GetSkillUseCaseTest extends WithMockRepositoryTest {

    @Test
    void execute() {

        GetSkillUseCase useCase = new GetSkillUseCase(this.skillRepository);
        SkillEntity skill = useCase.execute(preExistingSkill.getId());
        assertNotNull(skill);
        assertEquals(preExistingSkill, skill);
    }
}