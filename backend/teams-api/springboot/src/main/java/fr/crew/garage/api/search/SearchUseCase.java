package fr.crew.garage.api.search;

import fr.crew.garage.domain.search.Crew;
import fr.crew.garage.domain.search.CrewSearch;
import fr.crew.garage.domain.search.SearchDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Component
public class SearchUseCase {

    @Autowired
    SearchDomainService domainService;

    @Transactional
    public Collection<Crew> execute(List<CrewSearch> crewSearches) {

        return domainService.search(crewSearches);
    }
}
