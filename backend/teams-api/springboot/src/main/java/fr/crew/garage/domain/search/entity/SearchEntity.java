package fr.crew.garage.domain.search.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(
        name = "Search"
)
@Table(
        name = "SEARCH"
)
public class SearchEntity {

    @Column(
            name = "NAME"
    )
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

    @Id
    @GeneratedValue
    @Column(
            name = "ID"
    )
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "searchid", referencedColumnName = "id")
    private Set<CrewSearchEntity> searches;

}
