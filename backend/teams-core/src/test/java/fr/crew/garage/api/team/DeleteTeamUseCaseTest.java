package fr.crew.garage.api.team;

import fr.crew.garage.api.WithMockRepositoryTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeleteTeamUseCaseTest extends WithMockRepositoryTest {

    @Test
    void execute() {
        DeleteTeamUseCase useCase = new DeleteTeamUseCase(this.teamRepository);
        assertTrue(this.teamRepository.findAll().contains(preExistingTeam));
        useCase.execute(preExistingTeam);
        assertFalse(this.teamRepository.findAll().contains(preExistingTeam));
    }
}