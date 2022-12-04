package fr.crew.garage.api.search;

import fr.crew.garage.domain.search.entity.SearchEntity;
import fr.crew.garage.domain.search.repository.SearchRepository;
import org.modelmapper.ModelMapper;

public class GetSearchUseCase {

    final
    SearchRepository searchRepository;

    final
    ModelMapper mapper;


    public GetSearchUseCase(SearchRepository searchRepository, ModelMapper mapper) {
        this.searchRepository = searchRepository;
        this.mapper = mapper;
    }

    public SearchEntity execute(Long searchId) {
        return searchRepository.getById(searchId);

    }

}
