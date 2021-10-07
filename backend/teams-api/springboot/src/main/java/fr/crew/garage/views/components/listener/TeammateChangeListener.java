package fr.crew.garage.views.components.listener;

import fr.crew.garage.api.team.dto.TeamDTO;

public interface TeammateChangeListener {
    void onTeammateChange(TeamDTO team);
}
