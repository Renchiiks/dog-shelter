package com.example.dogshelter.controller;

import com.example.dogshelter.entity.Dog;
import com.example.dogshelter.service.DogService;
import com.example.dogshelter.service.ShelterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView addDog(@RequestBody Dog dog) {
        ModelAndView modelAndView = new ModelAndView("dog/home");
        modelAndView.addObject("shelters", shelterService.shelters());
        modelAndView.addObject("dog", dog);
        dogService.add(dog);

        return modelAndView;

    }

    @GetMapping("/getDog/{id}")
    public ModelAndView findDog(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("dog/showDog");
        Dog dog = dogService.findById(id);
        modelAndView.addObject(dog);

        return modelAndView;
    }

    @DeleteMapping("/removeDog/{id}")
    public ResponseEntity<Long> removeDog(@PathVariable Long id) {
        dogService.removeDog(id);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/getDogs/{shelterId}")
    public ModelAndView findDogsByShelterId(@PathVariable Long shelterId) {
        ModelAndView modelAndView = new ModelAndView("dog/showDogsByShelter");
        List<Dog> dogs = dogService.findByShelter(shelterId);
        modelAndView.addObject("dogs", dogs);

        return modelAndView;
    }

}
