package com.example.dogshelter.repository;

import com.example.dogshelter.entity.Dog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DogRepository extends CrudRepository<Dog, Long> {

    List<Dog> findByShelter (Long Id);

}
