package fr.crew.garage.in.rest;

import fr.crew.garage.api.search.SaveSearchUseCase;
import fr.crew.garage.api.search.SearchUseCase;
import fr.crew.garage.api.search.dto.CrewDTO;
import fr.crew.garage.api.search.dto.CrewSearchDTO;
import fr.crew.garage.api.search.dto.SearchDTO;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(value = "Search", tags = {"Search"})
public class SearchController {


    final
    SearchUseCase searchUseCase;

    final
    SaveSearchUseCase saveSearchUseCase;


    public SearchController(SearchUseCase searchUseCase, SaveSearchUseCase saveSearchUseCase) {
        this.searchUseCase = searchUseCase;
        this.saveSearchUseCase = saveSearchUseCase;
    }

    @PostMapping({"/search/execute"})
    public ResponseEntity<List<CrewDTO>> search(@Valid @RequestBody List<CrewSearchDTO> search) {
        List<CrewDTO> result = searchUseCase.execute(search);
        return ResponseEntity.ok(result);
    }

    @PostMapping({"/search/"})
    public ResponseEntity<SearchDTO> search(@Valid @RequestBody SearchDTO search) {
        return ResponseEntity.ok(saveSearchUseCase.execute(search));
    }


}
