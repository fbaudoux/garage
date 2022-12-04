package fr.crew.garage.api.team;

import fr.crew.garage.api.WithMockRepositoryTest;
import fr.crew.garage.domain.team.entity.TeamEntity;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class GetAllTeamsUseCaseTest extends WithMockRepositoryTest {

    @Test
    void execute() {
        GetAllTeamsUseCase useCase = new GetAllTeamsUseCase(this.teamRepository);
        Collection<TeamEntity> all = useCase.execute();
        assertNotNull(all);
        assertEquals(1, all.size());
        assertTrue(all.contains(preExistingTeam));
    }
}