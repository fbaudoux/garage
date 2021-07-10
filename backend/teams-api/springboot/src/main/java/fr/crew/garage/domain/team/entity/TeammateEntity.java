package fr.crew.garage.domain.team.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity(
        name = "Teammate"
)
@Table(
        name = "TEAMMATE"
)
public class TeammateEntity {

    @Id
    @GeneratedValue
    @Column(
            name = "ID"
    )
    private Long id;


    @Column(
            name = "NAME"
    )
    private String name;

    public TeammateEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
