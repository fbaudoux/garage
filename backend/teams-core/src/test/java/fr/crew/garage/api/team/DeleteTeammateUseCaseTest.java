package fr.crew.garage.api.team;

import fr.crew.garage.api.WithMockRepositoryTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeleteTeammateUseCaseTest extends WithMockRepositoryTest {

    @Test
    void execute() {
        DeleteTeammateUseCase useCase = new DeleteTeammateUseCase(this.teammateRepository);
        assertTrue(this.teammateRepository.findAll().contains(preExistingTeammate));
        useCase.execute(preExistingTeammate);
        assertFalse(this.teammateRepository.findAll().contains(preExistingTeammate));
    }
}