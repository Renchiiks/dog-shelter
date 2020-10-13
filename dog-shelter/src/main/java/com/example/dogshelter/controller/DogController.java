package com.example.dogshelter.controller;

import com.example.dogshelter.entity.Dog;
import com.example.dogshelter.service.DogService;
import com.example.dogshelter.service.ShelterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DogController {

    private DogService dogService;
    private ShelterService shelterService;

    public DogController(DogService dogService, ShelterService shelterService) {
        this.dogService = dogService;
        this.shelterService = shelterService;
    }

    @GetMapping("/dog-home")
    public String dog(Model model) {
        model.addAttribute("shelters", shelterService.shelters());
        return "dog/home";

    }

    @PostMapping("/dog")
    public String addDog(@ModelAttribute Dog dog, Model model) {

        model.addAttribute("shelters", shelterService.shelters());
        model.addAttribute("dog", new Dog());
        dogService.add(dog);
        return "dog/home";

    }

    @GetMapping("/getDog")
    public String findDog(@RequestParam Long id, Model model) {

        Dog dog = dogService.findById(id);
        model.addAttribute(dog);
        return "dog/showDog";
    }

    @DeleteMapping("/removeDog")
    public String removeDog(Long id) {
        dogService.removeDog(id);
        return "dog/home";
    }

    @GetMapping("/getDogs")
    public String findDogsByShelterId(@RequestParam Long id, Model model) {

        List<Dog> dogs = dogService.findByShelter(id);
        model.addAttribute("dogs", dogs);
        return "dog/showDogsByShelter";
    }

}
