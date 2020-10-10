package com.example.dogshelter.repository;

import com.example.dogshelter.entity.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Long> {

    Shelter findByName(String name);
}
