package fr.crew.garage.api.search;

import fr.crew.garage.api.WithMockRepositoryTest;
import fr.crew.garage.domain.search.Crew;
import fr.crew.garage.domain.search.SearchDomainService;
import fr.crew.garage.domain.search.entity.CrewSearchEntity;
import fr.crew.garage.domain.skill.entity.SkillEntity;
import fr.crew.garage.domain.team.entity.TeammateEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchUseCaseTest extends WithMockRepositoryTest {

    @Test
    void basicSearch_One_Skill() {

        SearchUseCase useCase = new SearchUseCase(new SearchDomainService(this.teammateRepository, this.teamRepository, this.skillRepository));

        CrewSearchEntity search = new CrewSearchEntity();
        search.getSkills().add(preExistingSkill);
        search.setName("Basic search");
        List<Crew> result = useCase.execute(List.of(search));
        assertNotNull(result);
        assertNull(result.get(0).getMembers().get(0).getTeammate());

        preExistingTeam.getTeammates().add(preExistingTeammate);
        preExistingTeammate.getSkills().add(preExistingSkill);

        result = useCase.execute(List.of(search));
        assertEquals(preExistingTeammate, result.get(0).getMembers().get(0).getTeammate());
    }

    @Test
    void basicSearch_Two_Time_Same_Skills() {

        SearchUseCase useCase = new SearchUseCase(new SearchDomainService(this.teammateRepository, this.teamRepository, this.skillRepository));

        SkillEntity newSkill = new SkillEntity();
        newSkill.setId(2L);
        newSkill.setName("newSkill");
        this.skillRepository.save(newSkill);

        TeammateEntity newTeammate = new TeammateEntity();
        newTeammate.setId(2L);
        newTeammate.setName("newTeammate");
        newTeammate.getSkills().add(newSkill);
        this.teammateRepository.save(newTeammate);

        CrewSearchEntity search = new CrewSearchEntity();
        search.getSkills().add(preExistingSkill);
        search.getSkills().add(newSkill);
        search.setName("Basic search");

        List<Crew> result = useCase.execute(List.of(search));
        assertNotNull(result);
        assertNull(result.get(0).getMembers().get(0).getTeammate());


        preExistingTeammate.getSkills().add(preExistingSkill);
        preExistingTeam.getTeammates().add(preExistingTeammate);
        preExistingTeam.getTeammates().add(newTeammate);


        result = useCase.execute(List.of(search));
        assertNotNull(result.get(0).getMembers().get(0).getTeammate());
        assertNotNull(result.get(0).getMembers().get(1).getTeammate());
    }


}