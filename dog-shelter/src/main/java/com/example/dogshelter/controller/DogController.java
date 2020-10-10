package com.example.dogshelter.controller;

import com.example.dogshelter.entity.Dog;
import com.example.dogshelter.entity.Shelter;
import com.example.dogshelter.repository.DogRepository;
import com.example.dogshelter.repository.ShelterRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DogController {

//    private final DogService dogService;
//
//    public DogController(DogService dogService) {
//        this.dogService = dogService;
//    }

    private DogRepository dogRepository;
    private ShelterRepository shelterRepository;

    public DogController(DogRepository dogRepository, ShelterRepository shelterRepository) {
        this.dogRepository = dogRepository;
        this.shelterRepository = shelterRepository;
    }

    @RequestMapping("/dog-home")
    public String dog(Model model) {
        model.addAttribute("shelters", shelterRepository.findAll());
        return "dog/home";

    }

    @RequestMapping("/dog")
    public String addDog( Dog dog, Model model) {
        if (dog != null) {
            model.addAttribute("shelters", shelterRepository.findAll());
            model.addAttribute("dog", new Dog());
            dogRepository.save(dog);
            return "dog/home";
        }
        return "dog/home";

    }

    @RequestMapping("/getDog")
    public String findDog(@RequestParam Long id, Model model) {

        Dog dog = dogRepository.findById(id).orElse(new Dog());
        model.addAttribute(dog);
        return "dog/showDog";
    }

    @RequestMapping("/removeDog")
    public String removeDog(Long id) {
        dogRepository.delete(dogRepository.findById(id).orElseThrow());
        return "dog/home";
    }

    @RequestMapping("/getDogs")
    public String findDogsByShelterId(@RequestParam Long id, Model model) {
        Shelter shelter = shelterRepository.findById(id).orElseThrow();
        List<Dog> dogs = dogRepository.findByShelter(shelter);
        model.addAttribute("dogs", dogs);
        return "dog/showDogsByShelter";
    }


}
