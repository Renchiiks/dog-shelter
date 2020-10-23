package com.example.dogshelter.service;

import com.example.dogshelter.entity.Shelter;
import com.example.dogshelter.repository.ShelterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShelterService {

    private final ShelterRepository shelterRepository;

    public ShelterService(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }

    public List<Shelter> shelters() {
        return (List<Shelter>) shelterRepository.findAll();
    }

    public Shelter findById(Long id) {
        return shelterRepository.findById(id).orElse(new Shelter());
    }

    public void addShelter(Shelter shelter) {
        shelterRepository.save(shelter);
    }

    public void removeShelter(Long id) {
        Shelter shelter = shelterRepository.findById(id).orElseThrow();
        shelterRepository.delete(shelter);
    }

    public Shelter findByName(String name) {
        return shelterRepository.findByName(name);
    }

}
