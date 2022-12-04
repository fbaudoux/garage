package fr.crew.garage.api.search;

import fr.crew.garage.domain.search.entity.SearchEntity;
import fr.crew.garage.domain.search.repository.SearchRepository;
import org.modelmapper.ModelMapper;

import java.util.List;

public class GetAllSearchsUseCase {

    final
    SearchRepository searchRepository;

    final
    ModelMapper mapper;

    public GetAllSearchsUseCase(SearchRepository searchRepository, ModelMapper mapper) {
        this.searchRepository = searchRepository;
        this.mapper = mapper;
    }

    public List<SearchEntity> execute() {
        return searchRepository.findAll();
    }
}
