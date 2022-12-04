package fr.crew.garage.api.team;

import fr.crew.garage.domain.team.entity.TeammateEntity;
import fr.crew.garage.domain.team.repository.TeammateRepository;

import java.util.Collection;

public class GetAllTeammatesUseCase {

    final
    TeammateRepository teammateRepository;


    public GetAllTeammatesUseCase(TeammateRepository teammateRepository) {
        this.teammateRepository = teammateRepository;
    }

    public Collection<TeammateEntity> execute() {

        /*List<TeammateDTO> dtos = teammateRepository.findAll()
                .stream()
                .map(teammate -> modelMapper.map(teammate, TeammateDTO.class))
                .collect(Collectors.toList());
                */


        return teammateRepository.findAll();
    }
}