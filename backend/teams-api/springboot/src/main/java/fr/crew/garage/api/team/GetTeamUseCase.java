package fr.crew.garage.api.team;

import fr.crew.garage.api.team.dto.TeamDTO;
import fr.crew.garage.domain.team.repository.TeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class GetTeamUseCase {
    final
    TeamRepository teamRepository;

    final
    ModelMapper modelMapper;

    public GetTeamUseCase(TeamRepository teamRepository, ModelMapper modelMapper) {
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public TeamDTO execute(Long teamId) {
        return modelMapper.map(teamRepository.getById(teamId), TeamDTO.class);
    }
}