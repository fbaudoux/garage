package fr.crew.garage.api.team;

import fr.crew.garage.api.WithMockRepositoryTest;
import fr.crew.garage.domain.team.entity.TeamEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetTeamUseCaseTest extends WithMockRepositoryTest {
    @Test
    void execute() {
        GetTeamUseCase useCase = new GetTeamUseCase(this.teamRepository);
        TeamEntity entity = useCase.execute(1L);
        assertEquals(this.preExistingTeam, entity);
    }
}