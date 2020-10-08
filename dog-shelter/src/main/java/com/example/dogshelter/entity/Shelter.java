package com.example.dogshelter.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Shelter {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String name;
    private String address;
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "shelter_id")
    private Set<Dog> dogs = new HashSet<>();

    public Shelter() {
    }

    public Shelter( String name, String address) {
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

    public Set<Dog> getDogs() {
        return dogs;
    }

    public void setDogs(Set<Dog> dogs) {
        this.dogs = dogs;
    }

    @Override
    public String toString() {
        return "Shelter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address;
    }

}
