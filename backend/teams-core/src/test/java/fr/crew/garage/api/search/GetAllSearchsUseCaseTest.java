package fr.crew.garage.api.search;

import fr.crew.garage.api.WithMockRepositoryTest;
import fr.crew.garage.domain.search.entity.SearchEntity;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetAllSearchsUseCaseTest extends WithMockRepositoryTest {

    @Test
    void execute() {
        GetAllSearchsUseCase getAllSearchsUseCase = new GetAllSearchsUseCase(searchRepository, new ModelMapper());
        List<SearchEntity> allSearch = getAllSearchsUseCase.execute();
        assertEquals(1, allSearch.size());


    }
}