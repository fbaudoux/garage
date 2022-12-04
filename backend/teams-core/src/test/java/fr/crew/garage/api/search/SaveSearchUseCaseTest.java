package fr.crew.garage.api.search;

import fr.crew.garage.api.WithMockRepositoryTest;
import fr.crew.garage.domain.search.entity.SearchEntity;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SaveSearchUseCaseTest extends WithMockRepositoryTest {


    @Test
    void execute() {

        GetAllSearchsUseCase getAllSearchsUseCase = new GetAllSearchsUseCase(searchRepository, new ModelMapper());
        List<SearchEntity> allSearch = getAllSearchsUseCase.execute();
        assertEquals(1, allSearch.size());

        SaveSearchUseCase useCase = new SaveSearchUseCase(this.searchRepository, new ModelMapper());
        SearchEntity toSave = new SearchEntity();
        toSave.setName("NEW SEARCH");
        toSave = useCase.execute(toSave);
        assertEquals("NEW SEARCH", toSave.getName());

        allSearch = getAllSearchsUseCase.execute();
        assertEquals(2, allSearch.size());

    }
}