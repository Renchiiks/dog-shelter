package com.example.dogshelter.repository;

import com.example.dogshelter.entity.Shelter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelterRepository extends CrudRepository<Shelter, Long> {
}
