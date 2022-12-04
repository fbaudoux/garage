package fr.crew.garage.domain.search.entity;

import java.util.Set;


public class SearchEntity {


    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<CrewSearchEntity> getSearches() {
        return searches;
    }

    public void setSearches(Set<CrewSearchEntity> searches) {
        this.searches = searches;
    }

    private Long id;
    private Set<CrewSearchEntity> searches;

}
