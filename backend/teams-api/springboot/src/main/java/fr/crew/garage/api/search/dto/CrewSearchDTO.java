package fr.crew.garage.api.search.dto;

import fr.crew.garage.api.skill.dto.SkillDTO;

import java.util.List;

public class CrewSearchDTO {

    private final String name;
    private final List<SkillDTO> skills;


    public CrewSearchDTO(String name, List<SkillDTO> skills) {
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
