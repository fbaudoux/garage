package fr.crew.garage.api.team;

import fr.crew.garage.api.WithMockRepositoryTest;
import fr.crew.garage.domain.team.entity.TeammateEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetTeammateUseCaseTest extends WithMockRepositoryTest {

    @Test
    void execute() {
        GetTeammateUseCase useCase = new GetTeammateUseCase(this.teammateRepository);
        TeammateEntity entity = useCase.execute(1L);
        assertEquals(this.preExistingTeammate, entity);
    }
}