package fr.crew.garage.api.search;

import fr.crew.garage.domain.search.Crew;
import fr.crew.garage.domain.search.SearchDomainService;
import fr.crew.garage.domain.search.entity.CrewSearchEntity;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

public class SearchUseCase {

    final
    SearchDomainService domainService;
    
    public SearchUseCase(SearchDomainService domainService) {
        this.domainService = domainService;
    }

    @Transactional
    public List<Crew> execute(List<CrewSearchEntity> crewSearches) {

        Collection<Crew> crews = domainService.search(crewSearches);
        return crews.stream().toList();

    }
}
