package fr.crew.garage.api.team;

import fr.crew.garage.domain.team.repository.TeamRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Component
public class StreamAllTeamsUseCase {
    final
    TeamRepository teamRepository;

    public StreamAllTeamsUseCase(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public void execute(SseEmitter emitter) {
        teamRepository.findAll().parallelStream().forEach(team -> {
            try {
                emitter.send(team);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}