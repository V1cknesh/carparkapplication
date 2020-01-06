package com.example.carparkapplication.repository;

import com.example.carparkapplication.request.Car;
import com.example.carparkapplication.response.CarRest;

import java.util.List;

public interface CarRepository {

    int count();

    int save(Car car);

    int update(Car car);

    int delete(int slot_number);

    List<CarRest> findAll();

    List<String> findRegistrationNumbersByColour(String colour);

    List<String> findSlotNumbersByColour(String colour);


    int getSlotNumberByRegistrationNumber(String registration_number);


}
