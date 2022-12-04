package fr.crew.garage.in.rest;

import fr.crew.garage.api.search.GetAllSearchsUseCase;
import fr.crew.garage.api.search.GetSearchUseCase;
import fr.crew.garage.api.search.SaveSearchUseCase;
import fr.crew.garage.api.search.SearchUseCase;
import fr.crew.garage.domain.search.Crew;
import fr.crew.garage.domain.search.SearchDomainService;

import fr.crew.garage.domain.search.entity.CrewSearchEntity;
import fr.crew.garage.domain.search.entity.SearchEntity;
import fr.crew.garage.domain.search.repository.SearchRepository;
import fr.crew.garage.domain.skill.repository.SkillRepository;
import fr.crew.garage.domain.team.repository.TeamRepository;
import fr.crew.garage.domain.team.repository.TeammateRepository;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
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


    public SearchController(TeammateRepository teammateRepository , TeamRepository teamRepository , SkillRepository skillRepository, SearchRepository searchRepository) {
        ModelMapper mapper = new ModelMapper();
        SearchDomainService searchDomainService = new SearchDomainService(teammateRepository,teamRepository,skillRepository,mapper);

        this.searchUseCase = new SearchUseCase(mapper,searchDomainService);
        this.saveSearchUseCase = new SaveSearchUseCase(searchRepository,mapper);
        this.getAllSearchs = new GetAllSearchsUseCase(searchRepository,mapper);
        this.getSearchUseCase = new GetSearchUseCase(searchRepository,mapper);
    }

    @PostMapping({"/search/execute"})
    public ResponseEntity<List<Crew>> search(@Valid @RequestBody List<CrewSearchEntity> search) {
        List<Crew> result = searchUseCase.execute(search);
        return ResponseEntity.ok(result);
    }

    @PostMapping({"/search/"})
    public ResponseEntity<SearchEntity> search(@Valid @RequestBody SearchEntity search) {
        return ResponseEntity.ok(saveSearchUseCase.execute(search));
    }


    @GetMapping("/searchs/")
    public ResponseEntity<List<SearchEntity>> getAllSearchs() {
        return ResponseEntity.ok(getAllSearchs.execute());
    }

    @GetMapping("/search/{searchId}/")
    public ResponseEntity<SearchEntity> getSearchById(@PathVariable(value = "searchId") Long searchId) {
        return ResponseEntity.ok(getSearchUseCase.execute(searchId));
    }


}
