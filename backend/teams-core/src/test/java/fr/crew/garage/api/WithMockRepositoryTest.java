package fr.crew.garage.api;

import fr.crew.garage.domain.search.entity.SearchEntity;
import fr.crew.garage.domain.search.repository.SearchRepository;
import fr.crew.garage.domain.skill.entity.SkillEntity;
import fr.crew.garage.domain.skill.repository.SkillRepository;
import fr.crew.garage.domain.team.entity.TeamEntity;
import fr.crew.garage.domain.team.entity.TeammateEntity;
import fr.crew.garage.domain.team.repository.TeamRepository;
import fr.crew.garage.domain.team.repository.TeammateRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class WithMockRepositoryTest {

    protected SkillRepository skillRepository;
    protected SkillEntity preExistingSkill;
    protected SearchRepository searchRepository;


    protected TeammateRepository teammateRepository;
    protected TeamRepository teamRepository;
    protected TeammateEntity preExistingTeammate;
    protected TeamEntity preExistingTeam;


    @BeforeEach
    void setUp() {
        createSkillFakeDatas();
        createSearchFakeDatas();
        createTeamFakeDatas();
    }

    private void createTeamFakeDatas() {
        this.teammateRepository = mock(TeammateRepository.class);
        this.teamRepository = mock(TeamRepository.class);

        this.preExistingTeammate = new TeammateEntity();
        this.preExistingTeammate.setName("preExistingTeammate");
        this.preExistingTeammate.setId(1L);

        this.preExistingTeam = new TeamEntity();
        this.preExistingTeam.setId(1L);
        this.preExistingTeam.setName("preExistingTeam");

        List<TeammateEntity> allTeammates = new ArrayList<>();
        allTeammates.add(preExistingTeammate);
        List<TeamEntity> allTeams = new ArrayList<>();
        allTeams.add(preExistingTeam);

        // mock findById
        when(this.teammateRepository.getById(1L)).thenReturn(preExistingTeammate);
        when(this.teamRepository.getById(1L)).thenReturn(preExistingTeam);

        //mock findAll
        when(this.teammateRepository.findAll()).thenReturn(allTeammates);
        when(this.teamRepository.findAll()).thenReturn(allTeams);
        when(this.teamRepository.findAll(1)).thenReturn(allTeams.subList(0, 1));


        // mock save
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                allTeammates.add(invocation.getArgument(0));
                return invocation.getArgument(0);
            }
        }).when(this.teammateRepository).save(any());

        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                allTeams.add(invocation.getArgument(0));
                return invocation.getArgument(0);
            }
        }).when(this.teamRepository).save(any());

        // mock delete
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                allTeammates.remove(preExistingTeammate);
                return invocation.getArgument(0);
            }
        }).when(this.teammateRepository).deleteById(1L);

        // mock delete
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                allTeams.remove(preExistingTeam);
                return invocation.getArgument(0);
            }
        }).when(this.teamRepository).deleteById(1L);
    }

    private void createSkillFakeDatas() {
        this.skillRepository = mock(SkillRepository.class);
        this.preExistingSkill = new SkillEntity();
        this.preExistingSkill.setName("preExistingSkill");
        this.preExistingSkill.setId(1L);
        List<SkillEntity> allSkills = new ArrayList<>();
        allSkills.add(this.preExistingSkill);

        // mock findById
        when(this.skillRepository.getById(1L)).thenReturn(preExistingSkill);

        // mock findAll
        when(this.skillRepository.findAll()).thenReturn(allSkills);

        // mock save
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                allSkills.add(invocation.getArgument(0));
                return invocation.getArgument(0);
            }
        }).when(this.skillRepository).save(any());

        // mock delete
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                allSkills.remove(invocation.getArgument(0));
                return invocation.getArgument(0);
            }
        }).when(this.skillRepository).deleteById(any());


    }

    private void createSearchFakeDatas() {
        this.searchRepository = mock(SearchRepository.class);
        SearchEntity entity1 = new SearchEntity();
        entity1.setName("SEARCH1");
        List<SearchEntity> allSearchs = new ArrayList<>();
        allSearchs.add(entity1);


        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                allSearchs.add(invocation.getArgument(0));
                return invocation.getArgument(0);
            }
        }).when(this.searchRepository).save(any());


        when(this.searchRepository.findAll()).thenReturn(allSearchs);
        when(this.searchRepository.getById(1L)).thenReturn(entity1);

    }

    @AfterEach
    void tearDown() {
    }

}
