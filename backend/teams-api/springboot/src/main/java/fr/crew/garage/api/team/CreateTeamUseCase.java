package fr.crew.garage.api.team;

import fr.crew.garage.api.team.dto.TeamDTO;
import fr.crew.garage.domain.team.TeamDomainService;
import fr.crew.garage.domain.team.entity.TeamEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CreateTeamUseCase {

    final
    TeamDomainService domainService;

    final
    ModelMapper modelMapper;

    public CreateTeamUseCase(TeamDomainService domainService, ModelMapper modelMapper) {
        this.domainService = domainService;
        this.modelMapper = modelMapper;
    }

    public TeamDTO execute(TeamDTO teamDTO) {

        TeamEntity teamEntity = domainService.createTeam(modelMapper.map(teamDTO, TeamEntity.class));
        return modelMapper.map(teamEntity, TeamDTO.class);
    }
}