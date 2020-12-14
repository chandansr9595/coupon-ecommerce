package com.chandan.rc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DBSeeder implements CommandLineRunner {

    private HotelRepository hotelRepository;

    public DBSeeder(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Hotel taj = new Hotel(
                "Taj",
                500,
                new Address("Mumbai", "India"),
                Arrays.asList(
                        new Review("Chandan", 8, true),
                        new Review("Karthik", 5, false)
                )
        );

        Hotel raj = new Hotel(
                "Raj",
                1000,
                new Address("Bangalore", "India"),
                Arrays.asList(
                        new Review("Chandan", 8, true)
                )
        );

        Hotel jewelRock = new Hotel(
                "Jewel Rock",
                1500,
                new Address("Shimoga", "India"),
                new ArrayList<>()
        );

        // Drop the existing collections
        this.hotelRepository.deleteAll();

        // Add the hotels to DB
        List<Hotel> hotelList = Arrays.asList(taj, raj, jewelRock);
        this.hotelRepository.saveAll(hotelList);
    }
}
