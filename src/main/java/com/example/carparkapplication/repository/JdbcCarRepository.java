package com.example.carparkapplication.repository;


import com.example.carparkapplication.request.Car;
import com.example.carparkapplication.response.CarRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class JdbcCarRepository implements CarRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public int count() {

        return jdbcTemplate.queryForObject("select count(*) from carpark", Integer.class);

    }

    @Override
    public int save(Car car) {

        return jdbcTemplate.update("insert into carpark (registration_number, colour) values(?,?)", car.getRegistration_number(), car.getColour());


    }

    @Override
    public int update(Car car) {

        int slot_number;

        try {

            slot_number = jdbcTemplate.queryForObject("select min(id) from carpark where registration_number = ? ", Integer.class, "Empty slot");

        } catch(Exception e) {

            slot_number = 0;

        }



        int number = (slot_number != 0) ? jdbcTemplate.update("update carpark set registration_number = ?, colour = ? where id = ?", car.getRegistration_number(), car.getColour(), slot_number) : 0;

        System.out.println(number);

        return (number != 0) ? slot_number : number;

    }

    @Override
    public int delete(int slot_number) {


        int slot_num;

        int deleted_slot_number;

        try {

            slot_num = jdbcTemplate.queryForObject("select id from carpark where id = ? ", Integer.class, slot_number);

        } catch(Exception e) {

            slot_num = 0;

        }

        if (slot_num != 0) {

            deleted_slot_number = jdbcTemplate.update("delete carpark where id = ?", slot_number);

            jdbcTemplate.update("insert into carpark (id, registration_number, colour) values(?, ?,?)",slot_number, "Empty slot", "Empty slot");

        } else {
            deleted_slot_number = 0;
        }




        return deleted_slot_number;
    }

    @Override
    public List<CarRest> findAll() {

        return jdbcTemplate.query("select * from carpark", (rs, rowNum) -> new CarRest( rs.getInt("id"), rs.getString("registration_number"), rs.getString("colour")));

    }

    @Override
    public List<String> findRegistrationNumbersByColour(String colour) {

        try {

            return jdbcTemplate.query("select registration_number from carpark where colour = ?", new Object[]{colour}, (rs, rowNum) -> rs.getString("registration_number"));

        } catch (Exception e) {

            List<String> empty_list = Arrays.asList(new String[]{"Not Found"});

            return empty_list;

        }


    }

    @Override
    public List<String> findSlotNumbersByColour(String colour) {


        try {

            return jdbcTemplate.query("select id from carpark where colour = ?", new Object[]{colour}, (rs, rowNum) -> rs.getInt("id") + "");

        } catch(Exception e) {

            List<String> empty_list = Arrays.asList(new String[]{"Not Found"});

            return empty_list;

        }




    }

    @Override
    public int getSlotNumberByRegistrationNumber(String registration_number) {

        try {

            return jdbcTemplate.queryForObject("select id from carpark where registration_number = ?", Integer.class, registration_number);

        } catch(Exception e) {

            return 0;

        }

    }
}
