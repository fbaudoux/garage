package fr.crew.garage.sgbd.search.repository;

import fr.crew.garage.domain.search.entity.SearchEntity;
import fr.crew.garage.domain.search.repository.SearchRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchRepositoryImpl implements SearchRepository {

    private SearchJpaRepository repo;
    private ModelMapper mapper;

    public SearchRepositoryImpl(SearchJpaRepository repo) {
        this.repo = repo;
        this.mapper = new ModelMapper();
    }

    @Override
    public List<SearchEntity> findAll() {
        return repo.findAll()
                .stream()
                .map(search -> mapper.map(search, SearchEntity.class))
                .collect(Collectors.toList());
    }

    @Override
    public SearchEntity save(SearchEntity toSave) {
        return mapper.map(repo.save(mapper.map(toSave, fr.crew.garage.sgbd.search.entity.SearchEntity.class)), SearchEntity.class);
    }

    @Override
    public SearchEntity getById(Long searchId) {
        return mapper.map(repo.getById(searchId),SearchEntity.class);
    }
}
