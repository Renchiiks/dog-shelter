package com.example.dogshelter.controller;

import com.example.dogshelter.entity.Dog;
import com.example.dogshelter.entity.Shelter;
import com.example.dogshelter.repository.DogRepository;
import com.example.dogshelter.repository.ShelterRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
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

    @Transactional
    @RequestMapping("/dog")
    public String addDog(Dog dog, Shelter shelter, Model model) {
        if(dog != null){

            dog.setShelter(shelter);
            model.addAttribute("shelter", shelterRepository.findAll());
            model.addAttribute("dog", dog);
            dogRepository.save(dog);
            return "dog/home";
        }
        return "dog/home";

    }

    @RequestMapping("/getDog")
    public ModelAndView findDog(@RequestParam Long id) {
        ModelAndView modelAndView = new ModelAndView("dog/showDog");
        Dog dog = dogRepository.findById(id).orElse(new Dog());
        modelAndView.addObject(dog);
        return modelAndView;
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
