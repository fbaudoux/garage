package fr.crew.garage.domain.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class SearchUseCase {

    @Autowired
    SearchDomainService domainService;

    public Collection<Crew> execute(List<CrewSearch> crewSearches) {

        return domainService.search(crewSearches);
    }
}
