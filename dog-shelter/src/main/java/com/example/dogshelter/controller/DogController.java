package com.example.dogshelter.controller;

import com.example.dogshelter.service.DogService;
import org.springframework.stereotype.Controller;

@Controller
public class DogController {

    private final DogService dogService;

    public DogController(DogService dogService) {
        this.dogService = dogService;
    }
}
