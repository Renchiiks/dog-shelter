package com.example.dogshelter.controller;

import com.example.dogshelter.entity.Dog;
import com.example.dogshelter.entity.Shelter;
import com.example.dogshelter.service.DogService;
import com.example.dogshelter.service.ShelterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class DogControllerTest {
    @Mock
    DogService dogService;
    @Mock
    ShelterService shelterService;
    @InjectMocks
    DogController controller;

    Shelter returnShelter;
    Dog dog1;
    List<Dog> dogs;
    List<Shelter> shelters;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        shelters = new ArrayList<>();
        dogs = new ArrayList<>();

        Shelter shelter = new Shelter();
        shelter.setId(1L);
        shelter.setName("Shelter");
        shelter.setAddress("Address");

        shelters.add(shelter);

        dog1 = new Dog();
        dog1.setId(1L);
        dog1.setName("Name");
        dog1.setAge(1);
        dog1.setBreed("unknown");
        dog1.setShelter(shelter);

        Dog dog2 = new Dog();
        dog2.setId(2L);
        dog2.setName("Name");
        dog2.setAge(1);
        dog2.setBreed("unknown");
        dog2.setShelter(shelter);

        dogs.add(dog1);
        dogs.add(dog2);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    @Test
    void dog() throws Exception {
        Mockito.when(shelterService.shelters()).thenReturn(shelters);

        mockMvc.perform(MockMvcRequestBuilders.get("/dog-home"))
                .andExpect(status().isOk())
                .andExpect(view().name("dog/home"))
                .andExpect(model().attribute("shelters", hasSize(1)));
    }

    @Test
    void addDog() throws Exception {

        dog1.setShelter(new Shelter());
        Mockito.when(dogService.add(Mockito.any())).thenReturn(dog1);

        Dog savedDog = dogService.add(dog1);

        assertEquals(1L, savedDog.getId());

        mockMvc.perform(MockMvcRequestBuilders.post("/dog")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"Name\", \"breed\": \"unknown\", \"age\": 1, " +
                        "\"Shelter\": null}")
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(view().name("dog/home"));
        verify(dogService).add(dog1);
    }

    @Test
    void findDogFound() throws Exception {

        when(dogService.findById(1L)).thenReturn(dog1);

        mockMvc.perform(MockMvcRequestBuilders.get("/getDog/{id}", 1))
                .andExpect(model().size(1))
                .andExpect(status().isOk())
                .andExpect(view().name("dog/showDog"));

        Dog dog = dogService.findById(1L);

        assertEquals(1L, dog.getId());
    }

    @Test
    void removeDog() {

        dogService.removeDog(1L);

        verify(dogService).removeDog(anyLong());
    }

    @Test
    void findDogsByShelterId() throws Exception {
        when(dogService.findByShelter(1L)).thenReturn(dogs);

        mockMvc.perform(MockMvcRequestBuilders.get("/getDogs/{shelterId}", 1))
                .andExpect(view().name("dog/showDogsByShelter"))
                .andExpect(model().attribute("dogs", hasSize(2)))
                .andExpect(status().isOk());


    }
}