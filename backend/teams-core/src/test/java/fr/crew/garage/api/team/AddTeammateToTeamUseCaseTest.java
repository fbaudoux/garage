package fr.crew.garage.api.team;

import fr.crew.garage.api.WithMockRepositoryTest;
import fr.crew.garage.domain.team.TeamDomainService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class AddTeammateToTeamUseCaseTest extends WithMockRepositoryTest {


    @Test
    void execute() {
        AddTeammateToTeamUseCase useCase = new AddTeammateToTeamUseCase(new TeamDomainService(this.teamRepository, this.teammateRepository), this.teamRepository, this.teammateRepository);
        assertFalse(preExistingTeam.getTeammates().contains(preExistingTeammate));
        useCase.execute(preExistingTeammate, preExistingTeam);
        assertFalse(preExistingTeam.getTeammates().contains(preExistingTeammate));
    }
}