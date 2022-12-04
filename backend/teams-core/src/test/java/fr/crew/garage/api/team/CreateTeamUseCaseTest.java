package fr.crew.garage.api.team;

import fr.crew.garage.api.WithMockRepositoryTest;
import fr.crew.garage.domain.team.TeamDomainService;
import fr.crew.garage.domain.team.entity.TeamEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateTeamUseCaseTest extends WithMockRepositoryTest {


    @Test
    void execute() {
        CreateTeamUseCase useCase = new CreateTeamUseCase(new TeamDomainService(this.teamRepository, this.teammateRepository));
        TeamEntity newEntity = new TeamEntity();
        newEntity.setName("new team");
        newEntity = useCase.execute(newEntity);
        assertEquals("new team", newEntity.getName());
    }
}