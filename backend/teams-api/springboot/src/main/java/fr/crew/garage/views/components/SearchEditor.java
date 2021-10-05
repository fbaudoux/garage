package fr.crew.garage.views.components;

import com.vaadin.componentfactory.multiselect.MultiComboBox;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import fr.crew.garage.api.search.SearchUseCase;
import fr.crew.garage.api.skill.GetAllSkillsUseCase;
import fr.crew.garage.api.skill.dto.SkillDTO;
import fr.crew.garage.domain.search.Crew;
import fr.crew.garage.domain.search.CrewSearch;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringComponent
@UIScope
public class SearchEditor extends VerticalLayout implements KeyNotifier {


    TextArea result = new TextArea("Result:");
    Button addCrewButton = new Button("Add a crew to the search", VaadinIcon.PLUS.create());
    Button searchButton = new Button("Search how to fill the crew", VaadinIcon.CHECK.create());
    Collection<SkillDTO> allSkills;
    SearchUseCase searchUseCase;
    VerticalLayout crewSearchListLayout = new VerticalLayout();
    List<MultiComboBox<SkillDTO>> crewSearchList = new ArrayList<>();
    Map<MultiComboBox, String> crewNames = new HashMap();
    int numberOfCrewToSearch = 1;

    @Autowired
    public SearchEditor(GetAllSkillsUseCase getAllSkillsUseCase, SearchUseCase searchUseCase) {

        this.searchUseCase = searchUseCase;

        allSkills = getAllSkillsUseCase.execute();

        TextField crewNameTextField = new TextField();
        crewNameTextField.setValue("Crew " + numberOfCrewToSearch++);

        MultiComboBox<SkillDTO> multiselectComboBox = new MultiComboBox();
        multiselectComboBox.setItems(allSkills);
        multiselectComboBox.setLabel("Select skills needed by this crew");
        multiselectComboBox.setWidth(50, Unit.PERCENTAGE);

        crewNames.put(multiselectComboBox, crewNameTextField.getValue());

        result.setWidth(100, Unit.PERCENTAGE);

        searchButton.addClickListener(e -> search());
        addCrewButton.addClickListener(e -> addCrew());

        crewSearchList.add(multiselectComboBox);
        crewSearchListLayout.add(crewNameTextField, multiselectComboBox);
        add(crewSearchListLayout, new HorizontalLayout(searchButton, addCrewButton), result);

    }

    private void addCrew() {
        TextField crewNameTextField = new TextField();
        crewNameTextField.setValue("Crew " + numberOfCrewToSearch++);
        MultiComboBox<SkillDTO> multiselectComboBox = new MultiComboBox();
        multiselectComboBox.setItems(allSkills);
        multiselectComboBox.setLabel("Select skills needed by this crew");
        multiselectComboBox.setWidth(50, Unit.PERCENTAGE);
        crewSearchListLayout.add(crewNameTextField, multiselectComboBox);
        crewSearchList.add(multiselectComboBox);
        crewNames.put(multiselectComboBox, crewNameTextField.getValue());
    }

    public void search() {

        List<CrewSearch> params = crewSearchList.stream().map(combo -> new CrewSearch(crewNames.get(combo), new ArrayList<>(combo.getValue()))).collect(Collectors.toList());
        Collection<Crew> execute = searchUseCase.execute(params);
        result.setValue("");
        if (execute.size() == 0) {
            result.setValue("SO SORRY, I CAN NOT FIND ANY SOLUTION");
        } else {
            result.setValue("I HAVE A SOLUTION FOR YOU :");
        }
        execute.forEach(crew ->
                result.setValue(result.getValue() + "\n" + crew)
        );
    }

    public void refreshSkill(Collection<SkillDTO> lst) {
        for (MultiComboBox box : this.crewSearchList) {
            box.setItems(lst);
        }
    }


}
