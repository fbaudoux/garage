package fr.crew.garage.domain.search;

import fr.crew.garage.domain.skill.entity.SkillEntity;
import fr.crew.garage.domain.team.entity.TeammateEntity;

import java.util.List;

public class Crew {

    List<CrewMember> members;

    public Crew(List<CrewMember> members) {
        this.members = members;
    }

    private class CrewMember {

        private TeammateEntity teammate;
        private SkillEntity skill;

    }
}
