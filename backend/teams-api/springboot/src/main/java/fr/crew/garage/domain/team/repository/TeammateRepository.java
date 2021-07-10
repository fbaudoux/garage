package fr.crew.garage.domain.team.repository;

import fr.crew.garage.domain.team.entity.TeammateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface TeammateRepository extends JpaRepository<TeammateEntity,Long> {


    @Query(
            value = "SELECT * FROM teammate t join membership m on m.teammateid = t.id WHERE m.teamid=:teamId",
            nativeQuery = true)
    Collection<TeammateEntity> findTeamMembers(@Param("teamId") Long teamId);
}
