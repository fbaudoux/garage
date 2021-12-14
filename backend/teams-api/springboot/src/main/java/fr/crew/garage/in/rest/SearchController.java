package fr.crew.garage.in.rest;

import fr.crew.garage.api.search.GetAllSearchsUseCase;
import fr.crew.garage.api.search.GetSearchUseCase;
import fr.crew.garage.api.search.SaveSearchUseCase;
import fr.crew.garage.api.search.SearchUseCase;
import fr.crew.garage.api.search.dto.CrewDTO;
import fr.crew.garage.api.search.dto.CrewSearchDTO;
import fr.crew.garage.api.search.dto.SearchDTO;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(value = "Search", tags = {"Search"})
public class SearchController {


    final
    SearchUseCase searchUseCase;

    final
    SaveSearchUseCase saveSearchUseCase;

    final GetAllSearchsUseCase getAllSearchs;

    final GetSearchUseCase getSearchUseCase;


    public SearchController(SearchUseCase searchUseCase, SaveSearchUseCase saveSearchUseCase, GetAllSearchsUseCase getAllSearchs, GetSearchUseCase getSearchUseCase) {
        this.searchUseCase = searchUseCase;
        this.saveSearchUseCase = saveSearchUseCase;
        this.getAllSearchs = getAllSearchs;
        this.getSearchUseCase = getSearchUseCase;
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


    @GetMapping("/searchs/")
    public ResponseEntity<List<SearchDTO>> getAllSearchs() {
        return ResponseEntity.ok(getAllSearchs.execute());
    }

    @GetMapping("/search/{searchId}/")
    public ResponseEntity<SearchDTO> getSearchById(@PathVariable(value = "searchId") Long searchId) {
        return ResponseEntity.ok(getSearchUseCase.execute(searchId));
    }


}
