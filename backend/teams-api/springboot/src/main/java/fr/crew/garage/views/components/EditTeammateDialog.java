package fr.crew.garage.views.components;

import com.vaadin.componentfactory.multiselect.MultiComboBox;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import fr.crew.garage.api.skill.AddSkillToTeammateUseCase;
import fr.crew.garage.api.skill.GetAllSkillsUseCase;
import fr.crew.garage.api.skill.dto.SkillDTO;
import fr.crew.garage.api.team.AddTeammateToTeamUseCase;
import fr.crew.garage.api.team.CreateTeammateUseCase;
import fr.crew.garage.api.team.DeleteTeammateUseCase;
import fr.crew.garage.api.team.dto.TeamDTO;
import fr.crew.garage.api.team.dto.TeammateDTO;
import fr.crew.garage.views.components.listener.TeammateChangeListener;

import java.util.List;
import java.util.Set;

public class EditTeammateDialog extends Dialog {

    MultiComboBox<SkillDTO> multiselectComboBox;
    TextField name;
    TeamDTO teamDTO;
    TeammateDTO teammateDTO;
    TeammateChangeListener teammateChangeListener;
    CreateTeammateUseCase createTeammateUseCase;
    GetAllSkillsUseCase getAllSkillsUseCase;
    AddTeammateToTeamUseCase addTeammateToTeamUseCase;
    AddSkillToTeammateUseCase addSkillToTeammateUseCase;
    DeleteTeammateUseCase deleteTeammateUseCase;


    public EditTeammateDialog(CreateTeammateUseCase createTeammateUseCase,
                              GetAllSkillsUseCase getAllSkillsUseCase,
                              AddTeammateToTeamUseCase addTeammateToTeamUseCase,
                              AddSkillToTeammateUseCase addSkillToTeammateUseCase,
                              DeleteTeammateUseCase deleteTeammateUseCase) {

        this.createTeammateUseCase = createTeammateUseCase;
        this.getAllSkillsUseCase = getAllSkillsUseCase;
        this.addTeammateToTeamUseCase = addTeammateToTeamUseCase;
        this.addSkillToTeammateUseCase = addSkillToTeammateUseCase;
        this.deleteTeammateUseCase = deleteTeammateUseCase;


        setWidth("400px");
        setHeight("350px");
        name = new TextField("Name");

        multiselectComboBox = new MultiComboBox();
        multiselectComboBox.setItems(getAllSkillsUseCase.execute());
        multiselectComboBox.setLabel("Select skills");
        Button save = new Button("Save", VaadinIcon.CHECK.create());
        Button delete = new Button("Delete", VaadinIcon.TRASH.create());
        HorizontalLayout allActions = new HorizontalLayout(save, delete);
        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");
        add(name, multiselectComboBox, allActions);

        save.addClickListener(e -> {
            save();
            close();
        });
        delete.addClickListener(e -> {
            delete();
            close();
        })
        ;
    }

    void save() {
        teammateDTO.setName(name.getValue());
        teammateDTO = createTeammateUseCase.execute(teammateDTO);
        addTeammateToTeamUseCase.execute(teammateDTO, teamDTO);

        Set<SkillDTO> skillEntities = multiselectComboBox.getValue();
        for (SkillDTO skillEntity : skillEntities) {
            addSkillToTeammateUseCase.execute(skillEntity, teammateDTO);
        }

        if (teammateChangeListener != null) {
            teammateChangeListener.onTeammateChange(this.teamDTO);
        }
    }

    void delete() {
        deleteTeammateUseCase.execute(teammateDTO);

        if (teammateChangeListener != null) {
            teammateChangeListener.onTeammateChange(this.teamDTO);
        }
    }

    public void setChangeListener(TeammateChangeListener teammateChangeListener) {
        this.teammateChangeListener = teammateChangeListener;
    }

    public void setTeamDTO(TeamDTO teamDTO) {
        this.teamDTO = teamDTO;
    }

    public void setTeammateDTO(TeammateDTO teammateDTO) {
        this.teammateDTO = teammateDTO;
        multiselectComboBox.setValue(teammateDTO.getSkills());
    }

    public void setSkills(List<SkillDTO> lst) {
        multiselectComboBox.setItems(lst);
    }


}
