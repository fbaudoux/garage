package fr.crew.garage.api.team;

import fr.crew.garage.api.team.dto.TeammateDTO;
import fr.crew.garage.domain.team.TeamDomainService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateTeammateUseCase {

    @Autowired
    TeamDomainService domainService;

    @Autowired
    ModelMapper modelMapper;

    public TeammateDTO execute(TeammateDTO mate) {
        return modelMapper.map(domainService.createTeammate(mate), TeammateDTO.class);
    }
}