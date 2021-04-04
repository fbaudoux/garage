package fr.crew.garage.application.team;

import fr.crew.garage.domain.team.TeamDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Component
public class StreamAllTeamsUseCase {
    @Autowired
    TeamDomainService domainService;

    public void execute(SseEmitter emitter) {
        domainService.getAllTeams().parallelStream().forEach(team -> {
            try {
                emitter.send(team);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}