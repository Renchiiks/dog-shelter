package com.example.dogshelter.controller;

import com.example.dogshelter.service.ShelterService;
import org.springframework.stereotype.Controller;

@Controller
public class ShelterController {

    private final ShelterService shelterService;

    public ShelterController(ShelterService shelterService) {
        this.shelterService = shelterService;
    }
}
