package fr.crew.dojo.domain.team.repository;

import fr.crew.dojo.domain.team.entity.TeamEntity;
import fr.crew.dojo.domain.team.entity.TeammateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class TeammateRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Collection<TeammateEntity> getAllTeammates() {
        return jdbcTemplate.query(
                "select * from teammate",
                (rs, rowNum) ->{
                    TeammateEntity entity = new TeammateEntity();
                    entity.setId(rs.getLong("id"));
                    entity.setName(rs.getString("name"));
                    return entity;
                }

        );
    }

    public void save(TeammateEntity newTeammate) {
        jdbcTemplate.update(
                "insert into teammate (name) values(?)",
                newTeammate.getName());
    }
}
