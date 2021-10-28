package fr.crew.garage.api.team;

import fr.crew.garage.api.team.dto.TeammateDTO;
import fr.crew.garage.domain.team.repository.TeammateRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class GetTeammateUseCase {
    final
    TeammateRepository teammateRepository;

    final
    ModelMapper modelMapper;

    public GetTeammateUseCase(TeammateRepository teammateRepository, ModelMapper modelMapper) {
        this.teammateRepository = teammateRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public TeammateDTO execute(Long teammateId) {
        return modelMapper.map(teammateRepository.getById(teammateId), TeammateDTO.class);
    }
}