package fr.crew.garage.api.team;

import fr.crew.garage.api.team.dto.TeamDTO;
import fr.crew.garage.domain.team.TeamDomainService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class GetAllTeamsPageByPageUseCase {

    @Autowired
    TeamDomainService domainService;

    @Autowired
    ModelMapper modelMapper;

    public Page<TeamDTO> execute(Integer pageNumber) {
        return domainService.getAllTeamsPageByPage(pageNumber).map(entity -> modelMapper.map(entity, TeamDTO.class));
    }
}