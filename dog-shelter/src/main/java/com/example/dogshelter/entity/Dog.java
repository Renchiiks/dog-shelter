package com.example.dogshelter.entity;

import javax.persistence.*;

@Entity
public class Dog {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String name;
    private String breed;
    private int age;
    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(foreignKey = @ForeignKey(name = "shelter_id"), name = "shelter_id")
    private Shelter shelter;

    public Dog() {
    }

    public Dog(String name, String breed, int age, Shelter shelter) {

        this.name = name;
        this.breed = breed;
        this.age = age;
        this.shelter = shelter;
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

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", age=" + age +
                ", shelter=" + shelter +
                '}';
    }

}
