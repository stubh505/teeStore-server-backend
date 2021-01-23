package com.teestore.backend.api;

import com.teestore.backend.model.Carousel;
import com.teestore.backend.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("CarouselAPI")
public class CarouselAPI {

    @Autowired
    private CarouselService carouselService;

    @GetMapping("/getAllCarousel")
    public ResponseEntity<List<Carousel>> getAllCarousel() throws Exception{
        try {
            List<Carousel> res = carouselService.getAllCarousel();
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
         catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/addCarousel")
    public ResponseEntity<String> addCarousel(@RequestBody Carousel carousel) throws Exception{

        try {
            return new ResponseEntity<>(carouselService.addCarousel(carousel), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/getCarouselById")
    public ResponseEntity<Carousel> getCarouselById(@RequestParam String carouselId) throws Exception{
        try {
            Carousel res = carouselService.getCarouselById(carouselId);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/removeCarousel")
    public ResponseEntity<String> removeCarousel(@RequestParam String carouselId) throws Exception{

        try {
            return new ResponseEntity<>(carouselService.removeCarousel(carouselId), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
    }

}
