package fr.crew.garage.domain.team.repository;

import fr.crew.garage.domain.team.entity.TeamEntity;

import java.util.List;


public interface TeamRepository {

    void addTeammateToTeam(Long teammateId, Long teamId);

    public TeamEntity findByName(String name);

    List<TeamEntity> findAll();

    TeamEntity getById(Long id);

    TeamEntity save(TeamEntity team);

    void deleteById(Long id);

    List<TeamEntity> findAll(Integer pageNumber);
}
