package fr.crew.garage.sgbd.search.repository;

import fr.crew.garage.sgbd.search.entity.SearchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchJpaRepository extends JpaRepository<SearchEntity, Long> {
}
