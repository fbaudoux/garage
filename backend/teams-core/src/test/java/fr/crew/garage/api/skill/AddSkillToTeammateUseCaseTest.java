package fr.crew.garage.api.skill;

import fr.crew.garage.api.WithMockRepositoryTest;
import fr.crew.garage.domain.skill.SkillDomainService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddSkillToTeammateUseCaseTest extends WithMockRepositoryTest {

    @Test
    void execute() {
        AddSkillToTeammateUseCase useCase = new AddSkillToTeammateUseCase(new SkillDomainService(this.skillRepository, this.teammateRepository), this.skillRepository, this.teammateRepository);
        assertFalse(preExistingTeammate.getSkills().contains(preExistingSkill));
        useCase.execute(preExistingSkill, preExistingTeammate);
        assertTrue(preExistingTeammate.getSkills().contains(preExistingSkill));
    }
}