package fr.crew.garage.api.team;

import fr.crew.garage.api.team.dto.TeamDTO;
import fr.crew.garage.api.team.dto.TeammateDTO;
import fr.crew.garage.domain.team.repository.TeamRepository;
import fr.crew.garage.domain.team.repository.TeammateRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class GetTeammateUseCase {
    @Autowired
    TeammateRepository teammateRepository;

    @Autowired
    ModelMapper modelMapper;

    @Transactional
    public TeammateDTO execute(Long teammateId) {
        return modelMapper.map(teammateRepository.getById(teammateId), TeammateDTO.class);
    }
}