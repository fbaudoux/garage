package fr.crew.garage.api.team;

import fr.crew.garage.api.team.dto.TeammateDTO;
import fr.crew.garage.domain.team.repository.TeammateRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class GetAllTeammatesUseCase {

    final
    TeammateRepository teammateRepository;

    final
    ModelMapper modelMapper;

    public GetAllTeammatesUseCase(TeammateRepository teammateRepository, ModelMapper modelMapper) {
        this.teammateRepository = teammateRepository;
        this.modelMapper = modelMapper;
    }

    public Collection<TeammateDTO> execute() {

        Collection<TeammateDTO> dtos = teammateRepository.findAll()
                .stream()
                .map(teammate -> modelMapper.map(teammate, TeammateDTO.class))
                .collect(Collectors.toList());

        return dtos;
    }
}