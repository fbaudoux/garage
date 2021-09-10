package fr.crew.garage.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import fr.crew.garage.application.team.AddTeammateToTeamUseCase;
import fr.crew.garage.domain.skill.AddSkillToTeammateUseCase;
import fr.crew.garage.domain.skill.GetAllSkillsUseCase;
import fr.crew.garage.domain.skill.entity.SkillEntity;
import fr.crew.garage.domain.team.CreateTeamUseCase;
import fr.crew.garage.domain.team.CreateTeammateUseCase;
import fr.crew.garage.domain.team.GetAllTeamsUseCase;
import fr.crew.garage.domain.team.command.AddTeammateToTeamRequest;
import fr.crew.garage.domain.team.entity.TeamEntity;
import fr.crew.garage.domain.team.entity.TeammateEntity;

import java.util.Collection;
import java.util.stream.Collectors;

@Route
public class MainView extends VerticalLayout {


    public MainView(GetAllTeamsUseCase getAllTeamsUseCase,
                    CreateTeamUseCase createTeamUseCase,
                    CreateTeammateUseCase createTeammateUseCase,
                    AddTeammateToTeamUseCase addTeammateToTeamUseCase,
                    GetAllSkillsUseCase getAllSkillsUseCase,
                    AddSkillToTeammateUseCase addSkillToTeammateUseCase) {

        Collection<TeamEntity> allTeams = getAllTeamsUseCase.execute();
        Collection<SkillEntity> allSkills = getAllSkillsUseCase.execute();


        Dialog dialog = new Dialog();

        TextField labelField = new TextField();
        labelField.setLabel("Name of the team");
        labelField.setClearButtonVisible(true);
        Button confirmButton = new Button("Confirm", event -> {

            createTeamUseCase.execute(labelField.getValue());
            dialog.close();
        });

        dialog.add(labelField, confirmButton);

        dialog.setWidth("400px");
        dialog.setHeight("250px");

        Button button = new Button("Add a team", VaadinIcon.PLUS.create());
        button.addClickListener(event -> dialog.open());

        add(new HorizontalLayout(new Label("Teams available : " + allTeams.size()), button));

        for (TeamEntity team : allTeams) {


            Dialog dialogCreateTeammate = new Dialog();

            TextField teammateNameField = new TextField();
            teammateNameField.setLabel("Name of the teammate");
            teammateNameField.setClearButtonVisible(true);

           /* MultiselectComboBox<SkillEntity> multiselectComboBox = new MultiselectComboBox();
            multiselectComboBox.setLabel("Select skills");
            multiselectComboBox.setItems(allSkills);*/

            Button createTeammateButton = new Button("Create teammate", event -> {
                TeammateEntity mate = createTeammateUseCase.execute(teammateNameField.getValue());
                addTeammateToTeamUseCase.execute(new AddTeammateToTeamRequest(mate.getId(), team.getId()));
                dialogCreateTeammate.close();
            });

            dialogCreateTeammate.add(teammateNameField, createTeammateButton);

            dialogCreateTeammate.setWidth("400px");
            dialogCreateTeammate.setHeight("250px");


            Button addNewBtn = new Button("New teammate", VaadinIcon.PLUS.create());
            addNewBtn.addClickListener(event -> dialogCreateTeammate.open());

            Grid grid = new Grid<>(TeammateEntity.class);
            // build layout
            HorizontalLayout actions = new HorizontalLayout(new Label(team.getName()), addNewBtn);
            add(actions, grid);

            grid.setHeight("200px");
            grid.setColumns("id", "name");
            grid.addComponentColumn(item -> new Label(((TeammateEntity) item).getSkills().stream().map(Object::toString)
                    .collect(Collectors.joining(", "))));
            grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);

            grid.setItems(team.getTeammates());
        }
    }


}
