package fr.crew.garage.domain.team;

import fr.crew.garage.domain.team.entity.TeamEntity;
import fr.crew.garage.domain.team.entity.TeammateEntity;
import fr.crew.garage.domain.team.repository.TeamRepository;
import fr.crew.garage.domain.team.repository.TeammateRepository;
import org.modelmapper.ModelMapper;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;


public class TeamDomainService {


    final
    TeamRepository teamRepository;

    final
    TeammateRepository teammateRepository;

    final
    ModelMapper modelMapper;

    public TeamDomainService(TeamRepository teamRepository, TeammateRepository teammateRepository) {
        this.teamRepository = teamRepository;
        this.teammateRepository = teammateRepository;
        this.modelMapper = new ModelMapper();
    }


    public Collection<TeammateEntity> getTeammatesForTeam(TeamEntity aTeam) {
        return teammateRepository.findTeamMembers(aTeam.getId());
    }

    public TeamEntity createTeam(TeamEntity team) {
        return teamRepository.save(team);
    }

    public TeammateEntity createTeammate(TeammateEntity newTeammate) {
        return teammateRepository.save(newTeammate);
    }

    @Transactional
    public void addTeammateToTeam(TeammateEntity teammate, TeamEntity team) {
        teamRepository.addTeammateToTeam(teammate.getId(), team.getId());
    }

    public List<TeamEntity> getAllTeamsPageByPage(Integer pageNumber) {
        return teamRepository.findAll(pageNumber);
    }
}
