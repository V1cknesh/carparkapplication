package com.example.carparkapplication.controller;

import com.example.carparkapplication.repository.CarRepository;
import com.example.carparkapplication.request.Car;
import com.example.carparkapplication.response.CarRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.IntStream;

@RestController
@RequestMapping("carpark")
public class CarParkController {

    @Autowired
    private CarRepository carRepository;

    @PostMapping("create_parking_lot")
    public String createParkingLot(@RequestParam(value = "number_of_slots") int number_of_slots) {

        Car car = new Car("Empty slot", "Empty Slot");

        IntStream.range(0, number_of_slots).forEach(i -> {carRepository.save(car);});

        System.out.println("Created parking lot with " + carRepository.count() + " slots");

        return "Created parking lot with " + carRepository.count() + " slots";

    }

    @PutMapping("park")
    public String parkInCarPark(@RequestParam(value = "registration_number") String registration_number, @RequestParam(value = "colour") String colour) {

        Car car  = new Car(registration_number, colour);

        int slot = carRepository.update(car);

        String message = (slot != 0) ? "Allocated slot number: " + slot : "Sorry, parking lot is full";

        System.out.println(message);

        return message ;


    }

    @DeleteMapping("leave")
    public String leaveCarpark(@RequestParam(value = "slot_number")  int slot_number) {

        int deleted_slot_number = carRepository.delete(slot_number);

        String message = (deleted_slot_number != 0) ? "Slot number " + deleted_slot_number + " is free" : "Not Found";

        System.out.println(message);

        return message;

    }

    @GetMapping(value = "status", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public List<CarRest>  getCarparkStatus() {

        List<CarRest> carList = carRepository.findAll();

        carList.forEach((n) -> System.out.println(String.join("  ", n.getSlot_number() + "", n.getRegistration_number(), n.getColour())) );

        return carList;

    }

    @GetMapping("registration_numbers_for_cars_with_colour")
    public String getRegistrationNumbersForCarWithColour(@RequestParam(value = "colour") String colour) {

        List<String> registration_numbers = carRepository.findRegistrationNumbersByColour(colour);

        String message;

        if (registration_numbers.get(0).equals("Not Found")) {

            message  = "Not Found";

        } else {

            message = String.join(", ", registration_numbers);

        }

        System.out.println(message);

        return message;


    }

    @GetMapping("slot_numbers_for_cars_with_colour")
    public String getSlotNumbersForCarWithColour(@RequestParam(value = "colour") String colour) {

        List<String> slot_numbers = carRepository.findSlotNumbersByColour(colour);

        String message;

        if (slot_numbers.get(0).equals("Not Found")) {

            message  = "Not Found";

        } else {

            message = String.join(", ", slot_numbers);

        }

        System.out.println(message);

        return message;

    }


    @GetMapping("slot_number_for_registration_number")
    public int getSlotNumberForRegistrationNumber(@RequestParam(value = "registration_number") String registration_number) {

        int slot_number = carRepository.getSlotNumberByRegistrationNumber(registration_number);

        if (slot_number == 0) {

            System.out.println(slot_number);

        } else {

            System.out.println("Not Found");

        }

        return slot_number;

    }

}
