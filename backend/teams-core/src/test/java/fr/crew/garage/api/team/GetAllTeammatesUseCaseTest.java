package fr.crew.garage.api.team;

import fr.crew.garage.api.WithMockRepositoryTest;
import fr.crew.garage.domain.team.entity.TeammateEntity;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class GetAllTeammatesUseCaseTest extends WithMockRepositoryTest {

    @Test
    void execute() {
        GetAllTeammatesUseCase useCase = new GetAllTeammatesUseCase(this.teammateRepository);
        Collection<TeammateEntity> all = useCase.execute();
        assertNotNull(all);
        assertEquals(1, all.size());
        assertTrue(all.contains(preExistingTeammate));
    }
}