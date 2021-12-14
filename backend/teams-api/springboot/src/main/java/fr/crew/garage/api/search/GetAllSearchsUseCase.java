package fr.crew.garage.api.search;

import fr.crew.garage.api.search.dto.SearchDTO;
import fr.crew.garage.domain.search.repository.SearchRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetAllSearchsUseCase {

    final
    SearchRepository searchRepository;

    final
    ModelMapper mapper;

    public GetAllSearchsUseCase(SearchRepository searchRepository, ModelMapper mapper) {
        this.searchRepository = searchRepository;
        this.mapper = mapper;
    }

    public List<SearchDTO> execute() {
        List<SearchDTO> dtos = searchRepository.findAll()
                .stream()
                .map(search -> mapper.map(search, SearchDTO.class))
                .collect(Collectors.toList());
        return dtos;
    }
}
