package fr.crew.garage.sgbd.skill.repository;

import fr.crew.garage.domain.skill.entity.SkillEntity;
import fr.crew.garage.domain.skill.repository.SkillRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SkillRepositoryImpl implements SkillRepository {
    private SkillJpaRepository repo;
    private ModelMapper mapper;

    public SkillRepositoryImpl(SkillJpaRepository repo) {
        this.repo = repo;
        this.mapper = new ModelMapper();
    }

    @Override
    public SkillEntity findByName(String name) {
        return this.mapper.map(this.repo.findByName(name), SkillEntity.class);
    }

    @Override
    public SkillEntity getById(Long id) {
        return this.mapper.map(this.repo.getById(id), SkillEntity.class);
    }

    @Override
    public void deleteById(Long id) {
        this.repo.deleteById(id);
    }

    @Override
    public List<SkillEntity> findAll() {
        return repo.findAll()
                .stream()
                .map(skill -> mapper.map(skill, SkillEntity.class))
                .collect(Collectors.toList());
    }

    @Override
    public SkillEntity save(SkillEntity skill) {
        return mapper.map(repo.save(mapper.map(skill, fr.crew.garage.sgbd.skill.entity.SkillEntity.class)), SkillEntity.class);
    }
}
