package fr.crew.garage.api.search;

import fr.crew.garage.domain.search.entity.SearchEntity;
import fr.crew.garage.domain.search.repository.SearchRepository;
import org.modelmapper.ModelMapper;

public class SaveSearchUseCase {
    final
    SearchRepository searchRepository;

    final
    ModelMapper mapper;

    public SaveSearchUseCase(SearchRepository searchRepository, ModelMapper mapper) {
        this.searchRepository = searchRepository;
        this.mapper = mapper;
    }

    public SearchEntity execute(SearchEntity search) {
        SearchEntity toSave = mapper.map(search, SearchEntity.class);
        return searchRepository.save(toSave);
    }
}
