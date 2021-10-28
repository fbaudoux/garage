package fr.crew.garage.api.team;

import fr.crew.garage.api.team.dto.TeamDTO;
import fr.crew.garage.domain.team.TeamDomainService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class GetAllTeamsPageByPageUseCase {

    final
    TeamDomainService domainService;

    final
    ModelMapper modelMapper;

    public GetAllTeamsPageByPageUseCase(TeamDomainService domainService, ModelMapper modelMapper) {
        this.domainService = domainService;
        this.modelMapper = modelMapper;
    }

    public Page<TeamDTO> execute(Integer pageNumber) {
        return domainService.getAllTeamsPageByPage(pageNumber).map(entity -> modelMapper.map(entity, TeamDTO.class));
    }
}