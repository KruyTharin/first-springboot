package com.example.first_springboot.student;

import jakarta.persistence.*;


@Entity
@Table(name = "Car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    @Column
    private  String name;

    @Column
    private String wheels;

    public Car() {
    }

    public Car(Long id, String name, String wheels) {
        this.id = id;
        this.name = name;
        this.wheels = wheels;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWheels() {
        return wheels;
    }

    public void setWheels(String wheels) {
        this.wheels = wheels;
    }
}
