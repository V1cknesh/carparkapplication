package com.example.carparkapplication.controller;

import com.example.carparkapplication.repository.CarRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CarParkControllerTest {

    @InjectMocks
    CarParkController carParkController;

    @Mock
    CarRepository carRepository;

    @BeforeEach
    void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

    }

    @AfterEach
    void tearDown() throws Exception {

    }

    @Test
    final void testGetSlotNumberByRegistrationNumber() {

        String registration_number = "KA-HH-9999";

        int slot_number_allocated = 3;

        when(carRepository.getSlotNumberByRegistrationNumber(anyString())).thenReturn(slot_number_allocated);

        int slot_number = carParkController.getSlotNumberForRegistrationNumber(registration_number);

        Assert.isTrue(slot_number == slot_number_allocated, "getSlotNumberForRegistrationNumber returns a slotnumber");

        Assert.notNull(slot_number, "slot_number returned is not null");


    }


    @Test
    final void testGetSlotNumberIfRegistrationNumberDoesNotExist() {

        String registration_number = "KA-HH-9999";

        int slot_number_allocated = 0;

        when(carRepository.getSlotNumberByRegistrationNumber(anyString())).thenReturn(0);

        int slot_number = carParkController.getSlotNumberForRegistrationNumber(registration_number);

        Assert.isTrue(slot_number == slot_number_allocated, "getSlotNumberForRegistrationNumber returns 0 when supplied a slot number that does not exist");

        Assert.notNull(slot_number, "slot_number returned is not null");


    }

    @Test
    public void testGetSlotNumbersForCarWithColour() {

        String colour = "White";

        String slot_number = "1, 2";

        List<String> empty_list = Arrays.asList(new String[]{"1", "2"});

        when(carRepository.findSlotNumbersByColour(anyString())).thenReturn(empty_list);

        String message = carParkController.getSlotNumbersForCarWithColour(colour);

        Assert.isTrue(message.equals(slot_number), "getSlotNumbersForCarWithColour returns slot_numbers");

        Assert.notNull(message, "slot_numbers returned are not null");

    }

    @Test
    public void testGetSlotNumbersForCarWithColourThatDoesNotExist() {

        String colour = "Green";

        String slot_number = "Not Found";

        List<String> empty_list = Arrays.asList(new String[]{"Not Found"});

        when(carRepository.findSlotNumbersByColour(anyString())).thenReturn(empty_list);

        String message = carParkController.getSlotNumbersForCarWithColour(colour);

        Assert.isTrue(message.equals(slot_number), "getSlotNumbersForCarWithColour returns Not Found");

        Assert.notNull(message, "slot_numbers returned are not null");

    }

    @Test
    final void testGetRegistrationNumberForCarWithColour() {

        String colour = "Blue";

        int slot_number_allocated = 0;

        when(carRepository.getSlotNumberByRegistrationNumber(anyString())).thenReturn(0);

        int slot_number = carParkController.getSlotNumberForRegistrationNumber(colour);

        Assert.isTrue(slot_number == slot_number_allocated, "getSlotNumberForRegistrationNumber returns 0 when supplied a slot number that does not exist");

        Assert.notNull(slot_number, "slot_number returned is not null");


    }

    @Test
    public void testGetRegistrationNumberForCarWithColourThatDoesNotExist() {

        String colour = "asdfasdf";

        String slot_number = "1, 2";

        List<String> empty_list = Arrays.asList(new String[]{"1", "2"});

        when(carRepository.findSlotNumbersByColour(anyString())).thenReturn(empty_list);

        String message = carParkController.getSlotNumbersForCarWithColour(colour);

        Assert.isTrue(message.equals(slot_number), "getSlotNumbersForCarWithColour returns slot_numbers");

        Assert.notNull(message, "slot_numbers returned are not null");

    }



}
