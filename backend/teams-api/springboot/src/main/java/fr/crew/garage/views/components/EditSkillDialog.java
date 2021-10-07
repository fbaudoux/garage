package fr.crew.garage.views.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;
import fr.crew.garage.api.skill.CreateSkillUseCase;
import fr.crew.garage.api.skill.DeleteSkillUseCase;
import fr.crew.garage.api.skill.dto.SkillDTO;
import fr.crew.garage.views.components.listener.SkillChangeListener;

public class EditSkillDialog extends Dialog {

    SkillDTO selectedSkill;
    TextField skillNameField;
    SkillChangeListener changeListener;
    CreateSkillUseCase createSkillUseCase;
    DeleteSkillUseCase deleteSkillUseCase;

    public void setSelectedSkill(SkillDTO selectedSkill) {
        this.selectedSkill = selectedSkill;
        skillNameField.setValue(this.selectedSkill.getName());
    }

    public void setChangeListener(SkillChangeListener changeListener) {
        this.changeListener = changeListener;
    }

    public EditSkillDialog(CreateSkillUseCase createSkillUseCase, DeleteSkillUseCase deleteSkillUseCase) {
        this.createSkillUseCase = createSkillUseCase;
        this.deleteSkillUseCase = deleteSkillUseCase;
        selectedSkill = new SkillDTO();

        setWidth("400px");
        setHeight("250px");

        //field for the name of the skill
        skillNameField = new TextField();
        skillNameField.setLabel("Name of the skill");
        skillNameField.setClearButtonVisible(true);

        //button to save a new skill
        Button confirmCreateTeamButton = new Button("Save", event -> {
            onSave();
        });

        //Button to delete a existing skill
        Button deleteSkillButton = new Button("Delete", VaadinIcon.TRASH.create());
        deleteSkillButton.addClickListener(e -> {
            onDelete();
        });

        //adding component to the dialog
        add(skillNameField, confirmCreateTeamButton, deleteSkillButton);
    }

    public void onSave() {
        selectedSkill.setName(skillNameField.getValue());
        createSkillUseCase.execute(selectedSkill);
        if (changeListener != null) {
            changeListener.onSkillChanged();
        }
        close();
    }

    public void onDelete() {
        deleteSkillUseCase.execute(selectedSkill);
        if (changeListener != null) {
            changeListener.onSkillChanged();
        }
        close();
    }

}
