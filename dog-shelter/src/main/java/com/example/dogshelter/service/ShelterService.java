package com.example.dogshelter.service;

import com.example.dogshelter.entity.Shelter;
import com.example.dogshelter.repository.ShelterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShelterService {

    private ShelterRepository shelterRepository;

    public void addShelter(Shelter shelter) {
        shelterRepository.save(shelter);
    }

    public void removeShelter(Shelter shelter) {
        shelterRepository.delete(shelter);
    }

    public Optional<Shelter> findById(Long id) {
        return shelterRepository.findById(id);
    }

    public Object findByName(String name) {
        List<Shelter> shelters = (List<Shelter>) shelterRepository.findAll();
        for (Shelter sh : shelters) {
            String shelterToFInd = sh.getName();
            if (shelterToFInd.equals(name)) {
                Long idToFind = sh.getId();
                return shelterRepository.findById(idToFind);
            }
        }
        return null;
    }

}
