package fr.crew.garage.views.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import fr.crew.garage.api.skill.CreateSkillUseCase;
import fr.crew.garage.api.skill.DeleteSkillUseCase;
import fr.crew.garage.api.skill.GetAllSkillsUseCase;
import fr.crew.garage.api.skill.dto.SkillDTO;
import fr.crew.garage.views.components.listener.SkillChangeListener;

public class SkillView extends VerticalLayout implements SkillChangeListener {

    GetAllSkillsUseCase getAllSkillsUseCase;
    Grid grid;
    SkillChangeListener changeListener;

    public void setChangeListener(SkillChangeListener changeListener) {
        this.changeListener = changeListener;
    }

    public SkillView(GetAllSkillsUseCase getAllSkillsUseCase, DeleteSkillUseCase deleteSkillUseCase, CreateSkillUseCase createSkillUseCase) {

        this.getAllSkillsUseCase = getAllSkillsUseCase;
        grid = new Grid<>(SkillDTO.class);
        Button addNewBtn = new Button("New skill", VaadinIcon.PLUS.create());

        HorizontalLayout actions = new HorizontalLayout(addNewBtn);
        add(actions, grid);
        grid.setHeight("200px");
        grid.setColumns("id", "name");
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);
        grid.setItems(getAllSkillsUseCase.execute());

        EditSkillDialog createSkillDialog = new EditSkillDialog(createSkillUseCase, deleteSkillUseCase);
        createSkillDialog.setChangeListener(this);

        addNewBtn.addClickListener(event -> createSkillDialog.open());

        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.addSelectionListener(e -> {
            if (e.getFirstSelectedItem().isPresent()) {
                createSkillDialog.setSelectedSkill((SkillDTO) e.getFirstSelectedItem().get());
                createSkillDialog.open();
            }
        });
    }

    @Override
    public void onSkillChanged() {
        grid.setItems(getAllSkillsUseCase.execute());
        grid.getDataProvider().refreshAll();
        if (changeListener != null) {
            changeListener.onSkillChanged();
        }
    }
}
