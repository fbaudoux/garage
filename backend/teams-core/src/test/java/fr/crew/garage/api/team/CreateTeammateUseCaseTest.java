package fr.crew.garage.api.team;

import fr.crew.garage.api.WithMockRepositoryTest;
import fr.crew.garage.domain.team.TeamDomainService;
import fr.crew.garage.domain.team.entity.TeammateEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateTeammateUseCaseTest extends WithMockRepositoryTest {

    @Test
    void execute() {

        CreateTeammateUseCase useCase = new CreateTeammateUseCase(new TeamDomainService(this.teamRepository, this.teammateRepository));
        TeammateEntity newEntity = new TeammateEntity();
        newEntity.setName("new teammate");
        newEntity = useCase.execute(newEntity);
        assertEquals("new teammate", newEntity.getName());

    }
}