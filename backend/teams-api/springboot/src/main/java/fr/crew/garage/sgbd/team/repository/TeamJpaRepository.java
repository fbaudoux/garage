package fr.crew.garage.sgbd.team.repository;

import fr.crew.garage.sgbd.team.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TeamJpaRepository extends JpaRepository<TeamEntity, Long> {

    @Modifying
    @Query(value = "insert into membership(teamId, teammateId) VALUES (:teamId, :teammateId)", nativeQuery = true)
    void addTeammateToTeam(@Param("teammateId") Long teammateId, @Param("teamId") Long teamId);

    public TeamEntity findByName(String name);
}
