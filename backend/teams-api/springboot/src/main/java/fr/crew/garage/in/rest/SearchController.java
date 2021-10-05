package fr.crew.garage.in.rest;

import fr.crew.garage.api.search.SearchUseCase;
import fr.crew.garage.api.search.dto.CrewDTO;
import fr.crew.garage.api.search.dto.CrewSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class SearchController {


    @Autowired
    SearchUseCase searchUseCase;

    public ResponseEntity<List<CrewDTO>> search(List<CrewSearchDTO> search) {
        List<CrewDTO> result = searchUseCase.execute(search);
        return ResponseEntity.ok(result);
    }

}
