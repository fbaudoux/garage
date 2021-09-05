package fr.crew.garage.domain.team.repository;

import fr.crew.garage.domain.team.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TeamRepository extends JpaRepository<TeamEntity, Long> {

    /*@Modifying
    @Query(value = "insert into membership(teamId, teammateId) VALUES (:teamId, :teammateId)", nativeQuery = true)
    void addTeammateToTeam(@Param("teammateId") Long teammateId,@Param("teamId") Long teamId);*/

    public TeamEntity findByName(String name);
}
