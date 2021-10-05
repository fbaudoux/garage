package fr.crew.garage.api.team;

import fr.crew.garage.api.team.dto.TeammateDTO;
import fr.crew.garage.domain.team.repository.TeammateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteTeammateUseCase {

    @Autowired
    TeammateRepository teammateRepository;

    public void execute(TeammateDTO teammateDTO) {
        teammateRepository.deleteById(teammateDTO.getId());
    }

}
