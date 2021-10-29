package fr.crew.garage.api.search.dto;

import java.util.List;

public class SearchDTO {

    private String name;
    private List<CrewSearchDTO> searches;

    public SearchDTO(String name, List<CrewSearchDTO> searches) {
        this.name = name;
        this.searches = searches;
    }

    public SearchDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CrewSearchDTO> getSearches() {
        return searches;
    }

    public void setSearches(List<CrewSearchDTO> searches) {
        this.searches = searches;
    }
}
