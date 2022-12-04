package fr.crew.garage.api.skill;

import fr.crew.garage.api.WithMockRepositoryTest;
import fr.crew.garage.domain.skill.entity.SkillEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GetAllSkillsUseCaseTest extends WithMockRepositoryTest {

    @Test
    void execute() {
        GetAllSkillsUseCase useCase = new GetAllSkillsUseCase(this.skillRepository);
        List<SkillEntity> all = useCase.execute();
        assertNotNull(all);
        assertEquals(1, all.size());
        assertTrue(all.contains(preExistingSkill));

    }
}