package com.example.dogshelter.repository;

import com.example.dogshelter.entity.Dog;
import com.example.dogshelter.entity.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {

    List<Dog> findByShelter (Shelter shelter);

}
