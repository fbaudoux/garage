package fr.crew.garage.api.team;

import fr.crew.garage.api.WithMockRepositoryTest;
import fr.crew.garage.domain.team.TeamDomainService;
import fr.crew.garage.domain.team.entity.TeamEntity;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class GetAllTeamsPageByPageUseCaseTest extends WithMockRepositoryTest {

    @Test
    void execute() {
        GetAllTeamsPageByPageUseCase useCase = new GetAllTeamsPageByPageUseCase(new TeamDomainService(this.teamRepository, this.teammateRepository));
        Collection<TeamEntity> all = useCase.execute(1);
        assertNotNull(all);
        assertEquals(1, all.size());
        assertTrue(all.contains(preExistingTeam));
    }


}
