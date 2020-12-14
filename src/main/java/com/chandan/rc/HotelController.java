package com.chandan.rc;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    private HotelRepository hotelRepository;

    public HotelController(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @GetMapping("/all")
    public List<Hotel> getAll() {
        return this.hotelRepository.findAll();
    }

    @PostMapping
    public void insert(@RequestBody Hotel hotel){
        this.hotelRepository.insert(hotel);
    }

    @PutMapping
    public void update(@RequestBody Hotel hotel) {
        this.hotelRepository.save(hotel);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id){
        this.hotelRepository.deleteById(id);
    }

    @PutMapping("/update")
    public void updatePricePerNight(@RequestBody Hotel hotel) {
        Optional<Hotel> optional = this.hotelRepository.findById(hotel.getId());
        optional.ifPresent(hotel1 -> {
            hotel1.setPricePerNight(hotel.getPricePerNight());
            this.hotelRepository.save(hotel1);
        });
    }

    @GetMapping("/{id}")
    public Hotel getById(@PathVariable("id") String id) {
        Optional<Hotel> optional = this.hotelRepository.findById(id);
        AtomicReference<Hotel> atomicReference = new AtomicReference<>();
        optional.ifPresent(hotel1 -> {
            atomicReference.set(hotel1);
        });
        return atomicReference.get();
    }

    @GetMapping("/price/{maxPrice}")
    public List<Hotel> getByPricePerNight(@PathVariable("maxPrice") int maxPrice){
        List<Hotel> hotels =  this.hotelRepository.findByPricePerNightLessThan(maxPrice);
        return hotels;
    }

}
