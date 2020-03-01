package fr.crew.dojo.domain.team;

import fr.crew.dojo.domain.team.entity.TeamEntity;
import fr.crew.dojo.domain.team.entity.TeammateEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class TeamDomainService {

    ConcurrentMap<Long, TeamEntity> teams = new ConcurrentHashMap<>();
    ConcurrentMap<Long, TeammateEntity> teammates = new ConcurrentHashMap<>();
    ConcurrentMap<TeamEntity, Collection<TeammateEntity>> members = new ConcurrentHashMap<>();


    public Collection<TeamEntity> getAllTeams() {
        return teams.values();
    }

    public TeamEntity getTeam(Long teamId) {
        return teams.get(teamId);
    }

    public Collection<TeammateEntity> getTeammatesForTeam(TeamEntity aTeam) {
        return members.get(aTeam);
    }

    public TeamEntity createTeam(String name) {
        TeamEntity newTeam = new TeamEntity();
        newTeam.setName(name);
        newTeam.setId(getNextTeamId());
        teams.putIfAbsent(newTeam.getId(),newTeam);
        return newTeam;
    }

    private Long getNextTeamId() {
        if (this.teams.isEmpty()) return 1L;
        Long max = this.teams.keySet().stream().max(Long::compareTo).get();
        return max+1;
    }


    public Collection<TeammateEntity> getAllTeammates() {
        return teammates.values();
    }

    public TeammateEntity createTeammate(String name) {
        TeammateEntity newTeammate = new TeammateEntity();
        newTeammate.setName(name);
        newTeammate.setId(getNextTeammateId());
        teammates.putIfAbsent(newTeammate.getId(),newTeammate);
        return newTeammate;
    }

    private Long getNextTeammateId() {
        if (this.teammates.isEmpty()) return 1L;

        Long max = this.teammates.keySet().stream().max(Long::compareTo).get();
        return max+1;
    }

    public int addTeammateToTeam(Long teammateId, Long teamId) {

        Collection<TeammateEntity> memberOfTheTeam = members.get(teams.get(teamId));
        if (memberOfTheTeam == null){
            memberOfTheTeam = new ArrayList<>();
            members.putIfAbsent(teams.get(teamId),memberOfTheTeam);
        }

        memberOfTheTeam.add(teammates.get(teammateId));
        return memberOfTheTeam.size();
    }
}
