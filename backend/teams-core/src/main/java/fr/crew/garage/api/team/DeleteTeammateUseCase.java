package fr.crew.garage.api.team;

import fr.crew.garage.domain.team.entity.TeammateEntity;
import fr.crew.garage.domain.team.repository.TeammateRepository;

public class DeleteTeammateUseCase {

    final
    TeammateRepository teammateRepository;

    public DeleteTeammateUseCase(TeammateRepository teammateRepository) {
        this.teammateRepository = teammateRepository;
    }

    public void execute(TeammateEntity teammateDTO) {
        teammateRepository.deleteById(teammateDTO.getId());
    }

}
