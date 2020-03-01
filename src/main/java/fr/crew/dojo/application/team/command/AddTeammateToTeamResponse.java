package fr.crew.dojo.application.team.command;

public class AddTeammateToTeamResponse {

    private final int numberOfMembers;

    public int getNumberOfMembers() {
        return numberOfMembers;
    }

    public AddTeammateToTeamResponse(int numberOfMembers){
        this.numberOfMembers = numberOfMembers;

    }
}
