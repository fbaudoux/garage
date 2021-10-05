package fr.crew.garage.api.search;

import fr.crew.garage.api.skill.dto.SkillDTO;
import fr.crew.garage.api.team.TeammateDTO;

public class CrewMemberDTO {

    private TeammateDTO teammate;
    private SkillDTO skill;

    public TeammateDTO getTeammate() {
        return teammate;
    }

    public void setTeammate(TeammateDTO teammate) {
        this.teammate = teammate;
    }

    public SkillDTO getSkill() {
        return skill;
    }

    public void setSkill(SkillDTO skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return
                "{ " + teammate +
                        ", skill=" + skill + " }";
    }
}
