package fr.crew.garage.sgbd.team.repository;

import fr.crew.garage.domain.team.entity.TeammateEntity;
import fr.crew.garage.domain.team.repository.TeammateRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeammateRepositoryImpl implements TeammateRepository{

    private TeammateJpaRepository repo;
    private ModelMapper mapper;

    public TeammateRepositoryImpl(TeammateJpaRepository repo) {

        this.repo = repo;
        mapper = new ModelMapper();
    }

    @Override
    public Collection<TeammateEntity> findTeamMembers(Long teamId) {
        return this.repo.findTeamMembers(teamId).stream()
                .map(teammate -> mapper.map(teammate, TeammateEntity.class))
                .collect(Collectors.toList());
    }

    @Override
    public TeammateEntity findByName(String name) {
        return  mapper.map(this.repo.findByName(name),TeammateEntity.class);
    }

    @Override
    public TeammateEntity getById(Long id) {
        return  mapper.map(this.repo.getById(id),TeammateEntity.class);
    }

    @Override
    public TeammateEntity save(TeammateEntity newTeammate) {
        return  mapper.map(this.repo.save(mapper.map(newTeammate, fr.crew.garage.sgbd.team.entity.TeammateEntity.class)),TeammateEntity.class);
    }

    @Override
    public List<TeammateEntity> findAll() {
        return this.repo.findAll().stream().map(teammate -> mapper.map(teammate, TeammateEntity.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        this.deleteById(id);

    }
}
