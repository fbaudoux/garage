package fr.crew.garage.views.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import fr.crew.garage.api.skill.AddSkillToTeammateUseCase;
import fr.crew.garage.api.skill.CreateSkillUseCase;
import fr.crew.garage.api.skill.DeleteSkillUseCase;
import fr.crew.garage.api.skill.GetAllSkillsUseCase;
import fr.crew.garage.api.team.AddTeammateToTeamUseCase;
import fr.crew.garage.api.team.CreateTeamUseCase;
import fr.crew.garage.api.team.CreateTeammateUseCase;
import fr.crew.garage.api.team.DeleteTeamUseCase;
import fr.crew.garage.api.team.DeleteTeammateUseCase;
import fr.crew.garage.api.team.GetAllTeamsUseCase;
import fr.crew.garage.api.team.GetTeamUseCase;
import fr.crew.garage.api.team.dto.TeamDTO;
import fr.crew.garage.api.team.dto.TeammateDTO;
import fr.crew.garage.views.components.listener.TeamChangeListener;
import fr.crew.garage.views.components.listener.TeammateChangeListener;

import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

public class TeamView extends VerticalLayout implements TeammateChangeListener, TeamChangeListener {

    CreateTeammateUseCase createTeammateUseCase;
    AddTeammateToTeamUseCase addTeammateToTeamUseCase;
    AddSkillToTeammateUseCase addSkillToTeammateUseCase;
    GetTeamUseCase getTeamUseCase;
    GetAllSkillsUseCase getAllSkillsUseCase;
    CreateSkillUseCase createSkillUseCase;
    DeleteTeammateUseCase deleteTeammateUseCase;
    DeleteTeamUseCase deleteTeamUseCase;
    DeleteSkillUseCase deleteSkillUseCase;
    CreateTeamUseCase createTeamUseCase;
    EditTeamDialog createTeamDialog;
    EditTeammateDialog editTeammateDialog;
    HashMap<TeamDTO, Grid> myGrids;

    public TeamView(GetAllTeamsUseCase getAllTeamsUseCase,
                    CreateTeamUseCase createTeamUseCase,
                    CreateTeammateUseCase createTeammateUseCase,
                    AddTeammateToTeamUseCase addTeammateToTeamUseCase,
                    GetAllSkillsUseCase getAllSkillsUseCase,
                    AddSkillToTeammateUseCase addSkillToTeammateUseCase,
                    GetTeamUseCase getTeamUseCase,
                    CreateSkillUseCase createSkillUseCase,
                    DeleteTeammateUseCase deleteTeammateUseCase,
                    DeleteTeamUseCase deleteTeamUseCase,
                    DeleteSkillUseCase deleteSkillUseCase) {


        this.createTeammateUseCase = createTeammateUseCase;
        this.addTeammateToTeamUseCase = addTeammateToTeamUseCase;
        this.addSkillToTeammateUseCase = addSkillToTeammateUseCase;
        this.getTeamUseCase = getTeamUseCase;
        this.createSkillUseCase = createSkillUseCase;
        this.getAllSkillsUseCase = getAllSkillsUseCase;
        this.deleteTeammateUseCase = deleteTeammateUseCase;
        this.deleteTeamUseCase = deleteTeamUseCase;
        this.deleteSkillUseCase = deleteSkillUseCase;
        this.createTeamUseCase = createTeamUseCase;

        this.createTeamDialog = new EditTeamDialog(createTeamUseCase);
        this.createTeamDialog.setChangeListener(this);

        this.editTeammateDialog = new EditTeammateDialog(createTeammateUseCase, getAllSkillsUseCase, addTeammateToTeamUseCase, addSkillToTeammateUseCase, deleteTeammateUseCase);
        this.editTeammateDialog.setChangeListener(this);

        Button addTeamButton = new Button("Add a team", VaadinIcon.PLUS.create());
        addTeamButton.addClickListener(event -> createTeamDialog.open());
        add(new HorizontalLayout(addTeamButton));

        myGrids = new HashMap<>();
        for (TeamDTO team : getAllTeamsUseCase.execute()) {
            createUIForTeam(team);
        }
    }

    private void createUIForTeam(TeamDTO team) {

        Button addNewBtn = new Button("New teammate", VaadinIcon.PLUS.create());
        Button deleteTeamBtn = new Button("Delete team", VaadinIcon.TRASH.create());


        addNewBtn.addClickListener(event -> {
            editTeammateDialog.setSkills(getAllSkillsUseCase.execute());
            editTeammateDialog.setTeamDTO(team);
            editTeammateDialog.setTeammateDTO(new TeammateDTO());
            editTeammateDialog.open();
        });

        Grid grid = new Grid<>(TeammateDTO.class);
        HorizontalLayout actions = new HorizontalLayout(new Label(team.getName()), deleteTeamBtn, addNewBtn);
        add(actions, grid);
        grid.setHeight("200px");
        grid.setColumns("id", "name");
        grid.addComponentColumn(item -> new Label(((TeammateDTO) item).getSkills().stream().map(Object::toString)
                .collect(Collectors.joining(", "))));
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);
        grid.setItems(team.getTeammates());

        grid.setSelectionMode(Grid.SelectionMode.SINGLE);

        grid.addSelectionListener(e -> {
            if (e.getFirstSelectedItem().isPresent()) {
                editTeammateDialog.setSkills(getAllSkillsUseCase.execute());
                editTeammateDialog.setTeamDTO(team);
                editTeammateDialog.setTeammateDTO(((TeammateDTO) e.getFirstSelectedItem().get()));
                editTeammateDialog.open();
            }
        });

        deleteTeamBtn.addClickListener(e -> {
            deleteTeam(team);
            remove(actions, grid);
        });

        myGrids.put(team, grid);
    }

    void deleteTeam(TeamDTO teamDTO) {
        deleteTeamUseCase.execute(teamDTO);
    }

    @Override
    public void onTeammateChange(TeamDTO team) {
        TeamDTO teamReloaded = getTeamUseCase.execute(team.getId());
        Set<TeammateDTO> teammates = teamReloaded.getTeammates();
        myGrids.get(team).setItems(teammates);
        myGrids.get(team).getDataProvider().refreshAll();
    }

    @Override
    public void onTeamChange(TeamDTO team) {
        createUIForTeam(team);
    }
}
