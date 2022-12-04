package fr.crew.garage.domain.team.repository;

import fr.crew.garage.domain.team.entity.TeammateEntity;

import java.util.Collection;
import java.util.List;

public interface TeammateRepository{
    Collection<TeammateEntity> findTeamMembers(Long teamId);

    public TeammateEntity findByName(String name);

    TeammateEntity getById(Long id);

    TeammateEntity save(TeammateEntity newTeammate);

    List<TeammateEntity> findAll();

    void deleteById(Long id);
}
