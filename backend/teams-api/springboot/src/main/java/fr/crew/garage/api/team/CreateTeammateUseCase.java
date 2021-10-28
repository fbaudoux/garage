package fr.crew.garage.api.team;

import fr.crew.garage.api.team.dto.TeammateDTO;
import fr.crew.garage.domain.team.TeamDomainService;
import fr.crew.garage.domain.team.entity.TeammateEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CreateTeammateUseCase {

    final
    TeamDomainService domainService;

    final
    ModelMapper modelMapper;

    public CreateTeammateUseCase(TeamDomainService domainService, ModelMapper modelMapper) {
        this.domainService = domainService;
        this.modelMapper = modelMapper;
    }

    public TeammateDTO execute(TeammateDTO mate) {
        return modelMapper.map(domainService.createTeammate(modelMapper.map(mate, TeammateEntity.class)), TeammateDTO.class);
    }
}