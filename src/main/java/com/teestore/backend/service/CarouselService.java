package com.teestore.backend.service;

import com.teestore.backend.model.Carousel;

import java.util.List;

public interface CarouselService {

    List<Carousel> getAllCarousel() throws Exception;
    String addCarousel(Carousel carousel) throws Exception;
    Carousel getCarouselById(String carouselId) throws Exception;
    String removeCarousel(String carouselId) throws Exception;
}
