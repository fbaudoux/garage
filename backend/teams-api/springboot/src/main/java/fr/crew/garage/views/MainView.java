package fr.crew.garage.views;

import com.vaadin.componentfactory.multiselect.MultiComboBox;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import fr.crew.garage.api.skill.AddSkillToTeammateUseCase;
import fr.crew.garage.api.skill.CreateSkillUseCase;
import fr.crew.garage.api.skill.DeleteSkillUseCase;
import fr.crew.garage.api.skill.GetAllSkillsUseCase;
import fr.crew.garage.api.skill.dto.SkillDTO;
import fr.crew.garage.api.team.AddTeammateToTeamUseCase;
import fr.crew.garage.api.team.CreateTeamUseCase;
import fr.crew.garage.api.team.CreateTeammateUseCase;
import fr.crew.garage.api.team.DeleteTeamUseCase;
import fr.crew.garage.api.team.DeleteTeammateUseCase;
import fr.crew.garage.api.team.GetAllTeamsUseCase;
import fr.crew.garage.api.team.GetTeamUseCase;
import fr.crew.garage.api.team.dto.TeamDTO;
import fr.crew.garage.api.team.dto.TeammateDTO;
import fr.crew.garage.views.components.SearchEditor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Route
public class MainView extends VerticalLayout {


    private final SearchEditor searchEditor;
    Collection<SkillDTO> allSkills;
    CreateTeammateUseCase createTeammateUseCase;
    AddTeammateToTeamUseCase addTeammateToTeamUseCase;
    AddSkillToTeammateUseCase addSkillToTeammateUseCase;
    GetTeamUseCase getTeamUseCase;
    GetAllSkillsUseCase getAllSkillsUseCase;
    CreateSkillUseCase createSkillUseCase;
    DeleteTeammateUseCase deleteTeammateUseCase;
    DeleteTeamUseCase deleteTeamUseCase;
    DeleteSkillUseCase deleteSkillUseCase;

    public MainView(GetAllTeamsUseCase getAllTeamsUseCase,
                    CreateTeamUseCase createTeamUseCase,
                    CreateTeammateUseCase createTeammateUseCase,
                    AddTeammateToTeamUseCase addTeammateToTeamUseCase,
                    GetAllSkillsUseCase getAllSkillsUseCase,
                    AddSkillToTeammateUseCase addSkillToTeammateUseCase,
                    GetTeamUseCase getTeamUseCase,
                    CreateSkillUseCase createSkillUseCase,
                    DeleteTeammateUseCase deleteTeammateUseCase,
                    DeleteTeamUseCase deleteTeamUseCase,
                    DeleteSkillUseCase deleteSkillUseCase,
                    SearchEditor searchEditor) {

        this.createTeammateUseCase = createTeammateUseCase;
        this.addTeammateToTeamUseCase = addTeammateToTeamUseCase;
        this.addSkillToTeammateUseCase = addSkillToTeammateUseCase;
        this.getTeamUseCase = getTeamUseCase;
        this.createSkillUseCase = createSkillUseCase;
        this.getAllSkillsUseCase = getAllSkillsUseCase;
        this.deleteTeammateUseCase = deleteTeammateUseCase;
        this.deleteTeamUseCase = deleteTeamUseCase;
        this.deleteSkillUseCase = deleteSkillUseCase;

        this.searchEditor = searchEditor;

        Collection<TeamDTO> allTeams = getAllTeamsUseCase.execute();
        allSkills = getAllSkillsUseCase.execute();


        /* NAVIGATION PART : TABS , PAGES and link between */
        Tab searchTab = new Tab("Search");
        Div searchPage = new Div();
        searchPage.setWidth(100, Unit.PERCENTAGE);

        Tab teamsTab = new Tab("Teams");
        Div teamsPage = new Div();
        teamsPage.setVisible(false);
        teamsPage.setWidth(100, Unit.PERCENTAGE);

        Tab skillsTab = new Tab("Skills");
        Div skillsPage = new Div();
        skillsPage.setVisible(false);
        skillsPage.setWidth(100, Unit.PERCENTAGE);

        Tabs tabs = new Tabs(searchTab, teamsTab, skillsTab);
        tabs.setWidth(100, Unit.PERCENTAGE);

        Map<Tab, Component> tabsToPages = new HashMap<>();
        tabsToPages.put(searchTab, searchPage);
        tabsToPages.put(teamsTab, teamsPage);
        tabsToPages.put(skillsTab, skillsPage);
        Div pages = new Div(searchPage, teamsPage, skillsPage);
        pages.setWidth(100, Unit.PERCENTAGE);

        tabs.addSelectedChangeListener(event -> {
            tabsToPages.values().forEach(page -> page.setVisible(false));
            Component selectedPage = tabsToPages.get(tabs.getSelectedTab());
            selectedPage.setVisible(true);
        });
        add(tabs);
        add(pages);


        Dialog createTeamDialog = new Dialog();
        TextField teamNameField = new TextField();
        teamNameField.setLabel("Name of the team");
        teamNameField.setClearButtonVisible(true);
        Button confirmCreateTeamButton = new Button("Confirm", event -> {
            TeamDTO newTeam = createTeamUseCase.execute(teamNameField.getValue());
            createTeamDialog.close();
            createUIForTeam(newTeam, teamsPage);
        });

        createTeamDialog.add(teamNameField, confirmCreateTeamButton);
        createTeamDialog.setWidth("400px");
        createTeamDialog.setHeight("250px");

        Button addTeamButton = new Button("Add a team", VaadinIcon.PLUS.create());
        addTeamButton.addClickListener(event -> createTeamDialog.open());

        searchPage.add(this.searchEditor);

        teamsPage.add(new HorizontalLayout(addTeamButton));

        for (TeamDTO team : allTeams) {
            createUIForTeam(team, teamsPage);
        }

        createSkillPage(skillsPage);
    }

    private void createUIForTeam(TeamDTO team, Div teamsPage) {
        Dialog dialogCreateTeammate = new Dialog();
        dialogCreateTeammate.setWidth("400px");
        dialogCreateTeammate.setHeight("350px");


        TextField name = new TextField("Name");
        Button deleteTeamBtn = new Button("Delete team", VaadinIcon.TRASH.create());

        MultiComboBox<SkillDTO> multiselectComboBox = new MultiComboBox();
        multiselectComboBox.setItems(allSkills);
        multiselectComboBox.setLabel("Select skills");
        Button save = new Button("Save", VaadinIcon.CHECK.create());
        Button delete = new Button("Delete", VaadinIcon.TRASH.create());
        HorizontalLayout allActions = new HorizontalLayout(save, delete);
        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        Button addNewBtn = new Button("New teammate", VaadinIcon.PLUS.create());
        TeammateDTO mate = new TeammateDTO();

        addNewBtn.addClickListener(event -> {
            mate.setId(null);
            multiselectComboBox.setItems(allSkills);
            dialogCreateTeammate.open();
        });

        Grid grid = new Grid<>(TeammateDTO.class);
        HorizontalLayout actions = new HorizontalLayout(new Label(team.getName()), deleteTeamBtn, addNewBtn);
        teamsPage.add(actions, grid);
        grid.setHeight("200px");
        grid.setColumns("id", "name");
        grid.addComponentColumn(item -> new Label(((TeammateDTO) item).getSkills().stream().map(Object::toString)
                .collect(Collectors.joining(", "))));
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);
        grid.setItems(team.getTeammates());


        save.addClickListener(e -> {
            save(multiselectComboBox, name, team, grid, mate);
            dialogCreateTeammate.close();
        });
        delete.addClickListener(e -> {
            delete(team, grid, mate);
            dialogCreateTeammate.close();
        })
        ;

        dialogCreateTeammate.add(name, multiselectComboBox, allActions);
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);

        grid.addSelectionListener(e -> {
            if (e.getFirstSelectedItem().isPresent()) {
                multiselectComboBox.setItems(allSkills);
                dialogCreateTeammate.open();
                mate.setId(((TeammateDTO) e.getFirstSelectedItem().get()).getId());
                name.setValue(((TeammateDTO) e.getFirstSelectedItem().get()).getName());
                multiselectComboBox.setValue(((TeammateDTO) e.getFirstSelectedItem().get()).getSkills());
            }
        });


        deleteTeamBtn.addClickListener(e -> {
            deleteTeam(team);
            teamsPage.remove(actions, grid);
        });


    }


    void deleteTeam(TeamDTO teamDTO) {
        deleteTeamUseCase.execute(teamDTO);
    }

    void delete(TeamDTO teamDTO, Grid grid, TeammateDTO mate) {
        deleteTeammateUseCase.execute(mate);
        TeamDTO teamReloaded = getTeamUseCase.execute(teamDTO.getId());
        Set<TeammateDTO> teammates = teamReloaded.getTeammates();
        grid.setItems(teammates);
        grid.getDataProvider().refreshAll();
    }

    void save(MultiComboBox<SkillDTO> multiselectComboBox, TextField name, TeamDTO teamDTO, Grid grid, TeammateDTO mate) {
        mate.setName(name.getValue());
        mate = createTeammateUseCase.execute(mate);

        addTeammateToTeamUseCase.execute(mate, teamDTO);

        Set<SkillDTO> skillEntities = multiselectComboBox.getValue();
        for (SkillDTO skillEntity : skillEntities) {
            addSkillToTeammateUseCase.execute(skillEntity, mate);
        }
        TeamDTO teamReloaded = getTeamUseCase.execute(teamDTO.getId());
        Set<TeammateDTO> teammates = teamReloaded.getTeammates();
        grid.setItems(teammates);
        grid.getDataProvider().refreshAll();


    }


    private void createSkillPage(Div skillsPage) {
        Grid grid = new Grid<>(SkillDTO.class);
        Button addNewBtn = new Button("New skill", VaadinIcon.PLUS.create());

        HorizontalLayout actions = new HorizontalLayout(addNewBtn);
        skillsPage.add(actions, grid);
        grid.setHeight("200px");
        grid.setColumns("id", "name");
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);
        grid.setItems(allSkills);

        SkillDTO selectedSkill = new SkillDTO();

        Dialog createSkillDialog = new Dialog();
        TextField skillNameField = new TextField();
        skillNameField.setLabel("Name of the skill");
        skillNameField.setClearButtonVisible(true);
        Button confirmCreateTeamButton = new Button("Save", event -> {
            selectedSkill.setName(skillNameField.getValue());
            createSkillUseCase.execute(selectedSkill);
            allSkills = getAllSkillsUseCase.execute();
            grid.setItems(allSkills);
            grid.getDataProvider().refreshAll();
            searchEditor.refreshSkill(allSkills);
            createSkillDialog.close();
        });

        Button deleteSkillButton = new Button("Delete", VaadinIcon.TRASH.create());

        createSkillDialog.add(skillNameField, confirmCreateTeamButton, deleteSkillButton);
        createSkillDialog.setWidth("400px");
        createSkillDialog.setHeight("250px");

        addNewBtn.addClickListener(event -> createSkillDialog.open());

        deleteSkillButton.addClickListener(e -> {
            deleteSkillUseCase.execute(selectedSkill);
            allSkills = getAllSkillsUseCase.execute();
            grid.setItems(allSkills);
            grid.getDataProvider().refreshAll();
            searchEditor.refreshSkill(allSkills);
            createSkillDialog.close();
        });


        grid.setSelectionMode(Grid.SelectionMode.SINGLE);

        grid.addSelectionListener(e -> {
            if (e.getFirstSelectedItem().isPresent()) {
                createSkillDialog.open();
                selectedSkill.setId(((SkillDTO) e.getFirstSelectedItem().get()).getId());
                skillNameField.setValue(((SkillDTO) e.getFirstSelectedItem().get()).getName());
            }
        });


    }


}
