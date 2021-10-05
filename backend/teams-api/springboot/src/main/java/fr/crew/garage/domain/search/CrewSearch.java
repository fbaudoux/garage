package fr.crew.garage.domain.search;

import fr.crew.garage.api.skill.dto.SkillDTO;

import java.util.List;

public class CrewSearch {

    private final String name;
    private final List<SkillDTO> skills;


    public CrewSearch(String name, List<SkillDTO> skills) {
        this.name = name;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public List<SkillDTO> getSkills() {
        return skills;
    }
}
