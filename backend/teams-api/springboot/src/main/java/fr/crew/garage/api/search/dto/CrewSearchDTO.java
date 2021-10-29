package fr.crew.garage.api.search.dto;

import fr.crew.garage.api.skill.dto.SkillDTO;

import java.util.List;

public class CrewSearchDTO {

    private String name;
    private List<SkillDTO> skills;

    public void setName(String name) {
        this.name = name;
    }

    public void setSkills(List<SkillDTO> skills) {
        this.skills = skills;
    }

    public CrewSearchDTO() {
    }

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
