package com.ebi.personrestapi.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "person")

public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "first_name")
    @NotEmpty(message = "Please provide your first name")
    private String first_name;

    @Column(name = "last_name")
    @NotEmpty(message = "Please provide your last name")
    private String last_name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "favourite_color")
    private String favourite_color;

    @ElementCollection
    private List<String> hobby = new ArrayList<>();

    public Person(String firstName,String lastName,Integer age,String favourite_color,List<String> hobby){
        this.first_name = firstName;
        this.last_name = lastName;
        this.age = age;
        this.favourite_color = favourite_color;
        this.hobby = hobby;
    }

    public Person(){
        //only for JPA
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getFavourite_color() {
        return favourite_color;
    }

    public void setFavourite_color(String favourite_color) {
        this.favourite_color = favourite_color;
    }

    public List<String> getHobby() {
        return hobby;
    }

    public void setHobby(List<String> hobby) {
        this.hobby = hobby;
    }

    /**
     * Refreshes the existing entity with the user requested latest details.
     * @param toUpdate - user requested entity to update
     */
    public void refresh(Person toUpdate) {
        first_name = toUpdate.first_name;
        last_name  = toUpdate.last_name;
        age = toUpdate.age;
        favourite_color = toUpdate.favourite_color;
        if(hobby!=null){
            hobby.clear();
            hobby.addAll(toUpdate.hobby);
        }
    }

    @Override
    public String toString() {
        return String.format("Person: {first_name: %s, last_name: %s, age: %s, favourite_color: %s}", first_name, last_name, age, favourite_color);
    }

}
