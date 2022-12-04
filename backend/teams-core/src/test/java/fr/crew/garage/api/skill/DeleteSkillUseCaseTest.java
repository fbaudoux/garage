package fr.crew.garage.api.skill;

import fr.crew.garage.api.WithMockRepositoryTest;
import fr.crew.garage.domain.skill.entity.SkillEntity;
import org.junit.jupiter.api.Test;

class DeleteSkillUseCaseTest extends WithMockRepositoryTest {

    @Test
    void execute() {
        DeleteSkillUseCase useCase = new DeleteSkillUseCase(this.skillRepository);
        SkillEntity entityToDelete = new SkillEntity();
        entityToDelete.setId(1L);
        useCase.execute(entityToDelete);
    }
}