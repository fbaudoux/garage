package fr.crew.garage.domain.team.command;

import fr.crew.garage.domain.team.entity.TeammateEntity;

import java.util.Collection;

public class GetAllTeammatesResponse {

    private final Collection<TeammateEntity> teammateList;

    public GetAllTeammatesResponse(Collection<TeammateEntity> teammateList) {
        this.teammateList = teammateList;
    }

    public Collection<TeammateEntity> getTeammateList() {
        return teammateList;
    }
}
