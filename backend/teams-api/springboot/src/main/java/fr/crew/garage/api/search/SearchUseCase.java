package fr.crew.garage.api.search;

import fr.crew.garage.api.search.dto.CrewDTO;
import fr.crew.garage.api.search.dto.CrewSearchDTO;
import fr.crew.garage.domain.search.Crew;
import fr.crew.garage.domain.search.CrewSearch;
import fr.crew.garage.domain.search.SearchDomainService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchUseCase {

    @Autowired
    SearchDomainService domainService;

    @Autowired
    ModelMapper modelMapper;

    @Transactional
    public List<CrewDTO> execute(List<CrewSearchDTO> crewSearches) {

        List<CrewSearch> searchs = crewSearches
                .stream()
                .map(crewSearch -> modelMapper.map(crewSearch, CrewSearch.class))
                .collect(Collectors.toList());

        Collection<Crew> crews = domainService.search(searchs);

        List<CrewDTO> res = crews
                .stream()
                .map(crewSearch -> modelMapper.map(crewSearch, CrewDTO.class))
                .collect(Collectors.toList());

        return res;

    }
}
