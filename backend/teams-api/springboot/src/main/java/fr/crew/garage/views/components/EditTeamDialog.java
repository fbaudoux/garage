package fr.crew.garage.views.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.textfield.TextField;
import fr.crew.garage.api.team.CreateTeamUseCase;
import fr.crew.garage.api.team.dto.TeamDTO;
import fr.crew.garage.views.components.listener.TeamChangeListener;

public class EditTeamDialog extends Dialog {

    TextField teamNameField;
    TeamChangeListener changeListener;

    public void setChangeListener(TeamChangeListener changeListener) {
        this.changeListener = changeListener;
    }

    public EditTeamDialog(CreateTeamUseCase createTeamUseCase) {
        setWidth("400px");
        setHeight("250px");

        teamNameField = new TextField();
        teamNameField.setLabel("Name of the team");
        teamNameField.setClearButtonVisible(true);
        
        Button confirmCreateTeamButton = new Button("Confirm", event -> {
            TeamDTO newTeam = createTeamUseCase.execute(teamNameField.getValue());
            this.close();
            if (this.changeListener != null) {
                this.changeListener.onTeamChange(newTeam);
            }
        });

        add(teamNameField, confirmCreateTeamButton);
    }
}
