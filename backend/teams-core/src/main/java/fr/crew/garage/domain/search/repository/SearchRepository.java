package fr.crew.garage.domain.search.repository;

import fr.crew.garage.domain.search.entity.SearchEntity;

import java.util.Arrays;
import java.util.List;

public interface SearchRepository {
    List<SearchEntity> findAll();

    SearchEntity save(SearchEntity toSave);

    SearchEntity getById(Long searchId);
}
