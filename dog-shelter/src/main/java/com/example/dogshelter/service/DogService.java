package com.example.dogshelter.service;

import com.example.dogshelter.entity.Dog;
import com.example.dogshelter.entity.Shelter;
import com.example.dogshelter.repository.DogRepository;
import com.example.dogshelter.repository.ShelterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DogService {

    private DogRepository dogRepository;
    private ShelterRepository shelterRepository;

    public DogService(DogRepository dogRepository, ShelterRepository shelterRepository) {
        this.dogRepository = dogRepository;
        this.shelterRepository = shelterRepository;
    }

    public void add(Dog dog) {

        dogRepository.save(dog);
    }

    public Dog findById(Long id) {
        return dogRepository.findById(id).orElseThrow();
    }

    public void removeDog(Long id) {
        Dog dog = dogRepository.findById(id).orElseThrow();
        dogRepository.delete(dog);
    }

    public List<Dog> findByShelter(Long id) {
        Shelter shelter = shelterRepository.findById(id).orElseThrow();
        return dogRepository.findByShelter(shelter);
    }

}
