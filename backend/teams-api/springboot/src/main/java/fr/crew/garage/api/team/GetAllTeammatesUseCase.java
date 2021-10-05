package fr.crew.garage.api.team;

import fr.crew.garage.api.team.dto.TeammateDTO;
import fr.crew.garage.domain.team.repository.TeammateRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class GetAllTeammatesUseCase {

    @Autowired
    TeammateRepository teammateRepository;

    @Autowired
    ModelMapper modelMapper;

    public Collection<TeammateDTO> execute() {

        Collection<TeammateDTO> dtos = teammateRepository.findAll()
                .stream()
                .map(teammate -> modelMapper.map(teammate, TeammateDTO.class))
                .collect(Collectors.toList());

        return dtos;
    }
}