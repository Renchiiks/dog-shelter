package com.example.dogshelter.controller;

import com.example.dogshelter.entity.Shelter;
import com.example.dogshelter.service.ShelterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String addShelter(Shelter shelter, Model model) {

        shelterService.addShelter(shelter);
        model.addAttribute("shelter", shelter);

        return "shelter/home";
    }

    @GetMapping("/getShelterById")
    public String getShelterById(@RequestParam Long id, Model model) {

        Shelter shelter = shelterService.findById(id);
        model.addAttribute(shelter);
        return "shelter/showShelter";
    }

    @GetMapping("/getShelterByName")
    public String getShelterByName(@RequestParam  String name, Model model) {
        Shelter shelter = shelterService.findByName(name);

        model.addAttribute(shelter);
        return "shelter/showShelter";
    }


    @DeleteMapping("/removeShelter")
    public String removeShelter(Long id) {
        shelterService.removeShelter(id);
        return "shelter/home";
    }
}
