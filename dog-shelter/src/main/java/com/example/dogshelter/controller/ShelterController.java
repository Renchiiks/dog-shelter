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

    @RequestMapping("/shelter-home")
    public String shelter() {
        return "shelter/home";
    }

    @RequestMapping("/shelter")
    public String addShelter(Shelter shelter, Model model) {

        shelterService.addShelter(shelter);
        model.addAttribute("shelter", shelter);

        return "shelter/home";
    }

    @RequestMapping("/getShelterById")
    public String getShelterById(@RequestParam Long id, Model model) {

        Shelter shelter = shelterService.findById(id);
        model.addAttribute(shelter);
        return "shelter/showShelter";
    }

    @RequestMapping ("/getShelterByName")
    public String getShelterByName(@RequestParam  String name, Model model) {
        Shelter shelter = shelterService.findByName(name);

        model.addAttribute(shelter);
        return "shelter/showShelter";
    }


    @RequestMapping("/removeShelter")
    public String removeShelter(Long id) {
        shelterService.removeShelter(id);
        return "shelter/home";
    }
}
