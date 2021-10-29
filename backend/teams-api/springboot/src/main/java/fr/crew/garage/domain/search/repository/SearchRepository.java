package fr.crew.garage.domain.search.repository;

import fr.crew.garage.domain.search.entity.SearchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchRepository extends JpaRepository<SearchEntity, Long> {
}
