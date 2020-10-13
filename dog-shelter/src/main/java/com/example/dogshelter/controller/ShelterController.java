package com.example.dogshelter.controller;

import com.example.dogshelter.entity.Shelter;
import com.example.dogshelter.service.ShelterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShelterController {

    private ShelterService shelterService;

    public ShelterController(ShelterService shelterService) {
        this.shelterService = shelterService;
    }

    @GetMapping("/shelter-home")
    public String shelter() {
        return "shelter/home";
    }

    @PostMapping("/shelter")
    public ModelAndView addShelter(@RequestBody Shelter shelter) {
        ModelAndView modelAndView = new ModelAndView("shelter/home");
        shelterService.addShelter(shelter);
        modelAndView.addObject("shelter", shelter);

        return modelAndView;
    }

    @GetMapping("/getShelterById/{id}")
    public ModelAndView getShelterById(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("shelter/showShelter");
        Shelter shelter = shelterService.findById(id);
        modelAndView.addObject(shelter);

        return modelAndView;
    }

    @GetMapping("/getShelterByName/{name}")
    public ModelAndView getShelterByName(@PathVariable String name) {
        ModelAndView modelAndView = new ModelAndView("shelter/showShelter");
        Shelter shelter = shelterService.findByName(name);
        modelAndView.addObject(shelter);

        return modelAndView;
    }


    @DeleteMapping("/removeShelter/{id}")
    public ResponseEntity<Long> removeShelter(@PathVariable Long id) {
        shelterService.removeShelter(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
