package fr.crew.garage.api.search;

import fr.crew.garage.api.search.dto.SearchDTO;
import fr.crew.garage.domain.search.entity.SearchEntity;
import fr.crew.garage.domain.search.repository.SearchRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SaveSearchUseCase {
    final
    SearchRepository searchRepository;

    final
    ModelMapper mapper;

    public SaveSearchUseCase(SearchRepository searchRepository, ModelMapper mapper) {
        this.searchRepository = searchRepository;
        this.mapper = mapper;
    }

    public SearchDTO execute(SearchDTO search) {
        SearchEntity toSave = mapper.map(search, SearchEntity.class);
        return mapper.map(searchRepository.save(toSave), SearchDTO.class);
    }
}
