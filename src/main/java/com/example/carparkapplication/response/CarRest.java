package com.example.carparkapplication.response;

public class CarRest {

    private int slot_number;

    private String registration_number;

    public String colour;

    public int getSlot_number() {
        return slot_number;
    }

    public CarRest(int slot_number, String registration_number, String colour) {
        this.slot_number = slot_number;
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
