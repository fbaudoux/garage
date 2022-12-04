package fr.crew.garage.sgbd.team.repository;

import fr.crew.garage.domain.team.entity.TeamEntity;
import fr.crew.garage.domain.team.repository.TeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeamRepositoryImpl implements TeamRepository {

    private TeamJpaRepository repo;
    private ModelMapper mapper;

    public TeamRepositoryImpl(TeamJpaRepository repo) {
        this.repo = repo;
        this.mapper = new ModelMapper();
    }

    @Override
    public void addTeammateToTeam(Long teammateId, Long teamId) {
        this.repo.addTeammateToTeam(teammateId, teamId);
    }

    @Override
    public TeamEntity findByName(String name) {
        return mapper.map(repo.findByName(name), TeamEntity.class);
    }

    @Override
    public List<TeamEntity> findAll() {
        return repo.findAll()
                .stream()
                .map(team -> mapper.map(team, TeamEntity.class))
                .collect(Collectors.toList());
    }

    @Override
    public TeamEntity getById(Long id) {
        return mapper.map(repo.getById(id), TeamEntity.class);
    }

    @Override
    public TeamEntity save(TeamEntity team) {
        return mapper.map(repo.save(mapper.map(team, fr.crew.garage.sgbd.team.entity.TeamEntity.class)), TeamEntity.class);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<TeamEntity> findAll(Integer pageNumber) {
        return repo.findAll(PageRequest.of(pageNumber, 10)).getContent().stream()
                .map(team -> mapper.map(team, TeamEntity.class))
                .collect(Collectors.toList());
    }
}
