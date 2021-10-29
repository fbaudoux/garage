package fr.crew.garage.api.search;

import fr.crew.garage.api.search.dto.CrewDTO;
import fr.crew.garage.api.search.dto.CrewSearchDTO;
import fr.crew.garage.domain.search.Crew;
import fr.crew.garage.domain.search.SearchDomainService;
import fr.crew.garage.domain.search.entity.CrewSearchEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchUseCase {

    final
    SearchDomainService domainService;

    final
    ModelMapper modelMapper;

    public SearchUseCase(ModelMapper modelMapper, SearchDomainService domainService) {
        this.modelMapper = modelMapper;
        this.domainService = domainService;
    }

    @Transactional
    public List<CrewDTO> execute(List<CrewSearchDTO> crewSearches) {

        List<CrewSearchEntity> searchs = crewSearches
                .stream()
                .map(crewSearch -> modelMapper.map(crewSearch, CrewSearchEntity.class))
                .collect(Collectors.toList());

        Collection<Crew> crews = domainService.search(searchs);

        List<CrewDTO> res = crews
                .stream()
                .map(crewSearch -> modelMapper.map(crewSearch, CrewDTO.class))
                .collect(Collectors.toList());

        return res;

    }
}
