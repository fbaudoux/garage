package fr.crew.dojo.domain.team.repository;

import fr.crew.dojo.domain.team.entity.TeamEntity;
import fr.crew.dojo.domain.team.entity.TeammateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

@Component
public class TeamRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Collection<TeamEntity> getAllTeams() {
        return jdbcTemplate.query(
                "select * from team",
                TeamRepository::mapRowTeam

        );
    }

    public TeamEntity get(Long teamId) {
        return jdbcTemplate.queryForObject(
                "select id,name from team where id = ?",
                new Object[]{teamId},
                TeamRepository::mapRowTeam
        );
    }

    public Collection<TeammateEntity> getMembers(TeamEntity aTeam) {
        return jdbcTemplate.query(
                "select t.id,t.name from teammate t join membership m on t.id = m.teammateid where m.teamid = ?",
                new Object[]{aTeam.getId()},
                TeamRepository::mapRowTeammate
        );
    }

    public void save(TeamEntity newTeam) {
         jdbcTemplate.update(
                "insert into team (name) values(?)",
                newTeam.getName());
    }

    public void addTeammateToTeam(Long teammateId, Long teamId) {
        jdbcTemplate.update(
                "insert into membership values(?,?)",
               teamId,teammateId);
    }

    private static TeamEntity mapRowTeam(ResultSet rs, int rowNum) throws SQLException {
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setId(rs.getLong("id"));
        teamEntity.setName(rs.getString("name"));
        return teamEntity;
    }

    private static TeammateEntity mapRowTeammate(ResultSet rs, int rowNum) throws SQLException {
        TeammateEntity entity = new TeammateEntity();
        entity.setId(rs.getLong("id"));
        entity.setName(rs.getString("name"));
        return entity;
    }
}
