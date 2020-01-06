package com.example.carparkapplication.request;


public class Car {


    private String registration_number;

    private String colour;

    public Car(String registration_number, String colour) {
        this.registration_number = registration_number;
        this.colour = colour;
    }

    public String getRegistration_number() {
        return registration_number;
    }

    public String getColour() {
        return colour;
    }

}
