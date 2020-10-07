package com.example.dogshelter.service;

import com.example.dogshelter.entity.Dog;
import com.example.dogshelter.repository.DogRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DogService {

    private DogRepository dogRepository;

    public void addDog(Dog dog) {
        dogRepository.save(dog);
    }

    public void removeDog(Dog dog) {
        dogRepository.delete(dog);
    }

    public Optional<Dog> findById(Long id) {
        return dogRepository.findById(id);
    }

}
