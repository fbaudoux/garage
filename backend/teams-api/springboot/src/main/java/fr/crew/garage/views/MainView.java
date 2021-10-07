package fr.crew.garage.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import fr.crew.garage.api.search.SearchUseCase;
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
import fr.crew.garage.views.components.SearchView;
import fr.crew.garage.views.components.SkillView;
import fr.crew.garage.views.components.TeamView;

import java.util.HashMap;
import java.util.Map;

@Route
public class MainView extends VerticalLayout {


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
                    SearchUseCase searchUseCase
    ) {

        this.createTeammateUseCase = createTeammateUseCase;
        this.addTeammateToTeamUseCase = addTeammateToTeamUseCase;
        this.addSkillToTeammateUseCase = addSkillToTeammateUseCase;
        this.getTeamUseCase = getTeamUseCase;
        this.createSkillUseCase = createSkillUseCase;
        this.getAllSkillsUseCase = getAllSkillsUseCase;
        this.deleteTeammateUseCase = deleteTeammateUseCase;
        this.deleteTeamUseCase = deleteTeamUseCase;
        this.deleteSkillUseCase = deleteSkillUseCase;


        // NAVIGATION PART : TABS , PAGES and link between
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
            tabsToPages.get(tabs.getSelectedTab()).setVisible(true);
        });
        add(tabs);
        add(pages);

        //View to manage the search
        SearchView searchView = new SearchView(this.getAllSkillsUseCase, searchUseCase);
        searchPage.add(searchView);

        //View to manage teams (CRUD) and teammate ( CRUD )
        teamsPage.add(new TeamView(getAllTeamsUseCase,
                createTeamUseCase,
                createTeammateUseCase,
                addTeammateToTeamUseCase,
                getAllSkillsUseCase,
                addSkillToTeammateUseCase,
                getTeamUseCase,
                createSkillUseCase,
                deleteTeammateUseCase,
                deleteTeamUseCase,
                deleteSkillUseCase));

        //View to manage skill (CRUD)
        SkillView skillView = new SkillView(getAllSkillsUseCase, deleteSkillUseCase, createSkillUseCase);
        skillView.setChangeListener(searchView);
        skillsPage.add(skillView);

    }
}
