package fr.crew.garage.in.rest;

import fr.crew.garage.api.search.SearchUseCase;
import fr.crew.garage.api.search.dto.CrewDTO;
import fr.crew.garage.api.search.dto.CrewSearchDTO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(value = "Search", tags = {"Search"})
public class SearchController {


    @Autowired
    SearchUseCase searchUseCase;

    @PostMapping({"/search/"})
    public ResponseEntity<List<CrewDTO>> search(@Valid @RequestBody List<CrewSearchDTO> search) {
        List<CrewDTO> result = searchUseCase.execute(search);
        return ResponseEntity.ok(result);
    }

}
