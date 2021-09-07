package fr.crew.garage.infrastructure.rest.team;

import fr.crew.garage.domain.search.Crew;
import fr.crew.garage.domain.search.CrewSearch;
import fr.crew.garage.domain.search.SearchUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.List;

public class SearchController {


    @Autowired
    SearchUseCase searchUseCase;

    public ResponseEntity<Collection<Crew>> search(List<CrewSearch> search) {
        Collection<Crew> result = searchUseCase.execute(search);
        return ResponseEntity.ok(result);
    }

}
