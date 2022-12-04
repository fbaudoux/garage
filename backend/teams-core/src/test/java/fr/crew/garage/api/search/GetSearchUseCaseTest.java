package fr.crew.garage.api.search;

import fr.crew.garage.api.WithMockRepositoryTest;
import fr.crew.garage.domain.search.entity.SearchEntity;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class GetSearchUseCaseTest extends WithMockRepositoryTest {

    @Test
    void execute() {

        GetSearchUseCase useCase = new GetSearchUseCase(this.searchRepository, new ModelMapper());
        SearchEntity dto = useCase.execute(Long.valueOf(1));
        assertNotNull(dto);
        dto = useCase.execute(Long.valueOf(1000));
        assertNull(dto);
    }
}