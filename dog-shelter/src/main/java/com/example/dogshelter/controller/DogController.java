package com.example.dogshelter.controller;

import com.example.dogshelter.entity.Dog;
import com.example.dogshelter.repository.DogRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DogController {

//    private final DogService dogService;
//
//    public DogController(DogService dogService) {
//        this.dogService = dogService;
//    }

    private DogRepository dogRepository;

    public DogController(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @RequestMapping("/addDog")
    public String addDog(Dog dog) {
        dogRepository.save(dog);
        return "dog/dog";
    }

    @RequestMapping("/getDog")
    public ModelAndView findDog(@RequestParam Long id) {
        ModelAndView modelAndView = new ModelAndView("dog/showDog");
        Dog dog = dogRepository.findById(id).orElse(new Dog());
        modelAndView.addObject(dog);
        return modelAndView;
    }
}
