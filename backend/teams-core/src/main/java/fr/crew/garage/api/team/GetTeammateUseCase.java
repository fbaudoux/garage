package fr.crew.garage.api.team;

import fr.crew.garage.domain.team.entity.TeammateEntity;
import fr.crew.garage.domain.team.repository.TeammateRepository;

import javax.transaction.Transactional;

public class GetTeammateUseCase {
    final
    TeammateRepository teammateRepository;


    public GetTeammateUseCase(TeammateRepository teammateRepository) {
        this.teammateRepository = teammateRepository;
    }

    @Transactional
    public TeammateEntity execute(Long teammateId) {
        return teammateRepository.getById(teammateId);
    }
}