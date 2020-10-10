package com.example.dogshelter.controller;

import com.example.dogshelter.entity.Shelter;
import com.example.dogshelter.repository.ShelterRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShelterController {

    //private ShelterService shelterService;
    private ShelterRepository shelterRepository;

    public ShelterController(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }

    //    public ShelterController(ShelterService shelterService) {
//        this.shelterService = shelterService;
//    }

    @RequestMapping("/shelter")
    public String addShelter(Shelter shelter) {
        if(shelter!= null){
            shelterRepository.save(shelter);
        }
        return "shelter/home";
    }

    @RequestMapping("getShelterById")
    public ModelAndView getShelterById(@RequestParam Long id) {
        ModelAndView modelAndView = new ModelAndView("shelter/showShelter");
        Shelter shelter = shelterRepository.findById(id).orElse(new Shelter());
        modelAndView.addObject(shelter);
        return modelAndView;
    }

    @RequestMapping("getShelterByName")
    public ModelAndView getShelterByName(@RequestParam String name) {
        ModelAndView modelAndView = new ModelAndView("shelter/showShelter");
        Iterable<Shelter> shelters = shelterRepository.findAll();
        for (Shelter shelter : shelters) {
            if (shelter.getName().equals(name)) {
                modelAndView.addObject(shelter);
                return modelAndView;
            }
        }
        return null;
    }

    @RequestMapping("removeShelter")
    public String removeShelter(Long id) {
        shelterRepository.delete(shelterRepository.findById(id).orElseThrow());
        return "shelter/home";
    }
}
