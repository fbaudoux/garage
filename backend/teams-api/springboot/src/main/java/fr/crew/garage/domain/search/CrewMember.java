package fr.crew.garage.domain.search;

import fr.crew.garage.domain.skill.entity.SkillEntity;
import fr.crew.garage.domain.team.entity.TeammateEntity;

public class CrewMember {

    private TeammateEntity teammate;
    private SkillEntity skill;

    public TeammateEntity getTeammate() {
        return teammate;
    }

    public void setTeammate(TeammateEntity teammate) {
        this.teammate = teammate;
    }

    public SkillEntity getSkill() {
        return skill;
    }

    public void setSkill(SkillEntity skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return
                "{ " + teammate +
                        ", skill=" + skill + " }";
    }
}
