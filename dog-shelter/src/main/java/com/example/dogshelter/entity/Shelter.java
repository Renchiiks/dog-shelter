package com.example.dogshelter.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Shelter {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String name;
    private String address;

    @OneToMany(mappedBy = "shelter")
    private List<Dog> dogs = new ArrayList<>();

    public Shelter() {
    }

    public Shelter(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Dog> getDogs() {
        return dogs;
    }

    public void setDogs(List<Dog> dogs) {
        this.dogs = dogs;
    }

    @PreRemove
    private void preRemove() {
        for (Dog dog : dogs) {
            dog.setShelter(null);
        }
    }

    @Override
    public String toString() {
        return "Shelter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address;
    }

}
