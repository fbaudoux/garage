package fr.crew.garage.api.team;

import fr.crew.garage.api.team.dto.TeamDTO;
import fr.crew.garage.domain.team.repository.TeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class GetTeamUseCase {
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    ModelMapper modelMapper;

    @Transactional
    public TeamDTO execute(Long teamId) {
        return modelMapper.map(teamRepository.getById(teamId), TeamDTO.class);
    }
}