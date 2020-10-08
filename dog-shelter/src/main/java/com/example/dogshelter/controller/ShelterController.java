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
        shelterRepository.save(shelter);
        return "shelter/shelter";
    }

    @RequestMapping("getShelter")
    public ModelAndView getShelter(@RequestParam Long id) {
        ModelAndView modelAndView = new ModelAndView("shelter/showShelter");
        Shelter shelter = shelterRepository.findById(id).orElse(new Shelter());
        modelAndView.addObject(shelter);
        return modelAndView;
    }

    @RequestMapping("removeShelter")
    public String removeShelter(Long id) {
        shelterRepository.delete(shelterRepository.findById(id).orElseThrow());
        return "shelter/shelter";
    }
}
