package fr.crew.garage.api.search;

import fr.crew.garage.api.search.dto.SearchDTO;
import fr.crew.garage.domain.search.entity.SearchEntity;
import fr.crew.garage.domain.search.repository.SearchRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GetSearchUseCase {

    final
    SearchRepository searchRepository;

    final
    ModelMapper mapper;


    public GetSearchUseCase(SearchRepository searchRepository, ModelMapper mapper) {
        this.searchRepository = searchRepository;
        this.mapper = mapper;
    }

    public SearchDTO execute(Long searchId) {
        SearchEntity entity = searchRepository.getById(searchId);
        return mapper.map(entity, SearchDTO.class);
    }

}
