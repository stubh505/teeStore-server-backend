package com.teestore.backend.dao;

import com.teestore.backend.model.Carousel;

import java.util.List;

public interface CarouselDAO {

    List<Carousel> getAllCarousel() throws Exception;
    String addCarousel(Carousel carousel) throws Exception;
    Carousel getCarouselById(String carouselId) throws Exception;
    String removeCarousel(String carouselId) throws Exception;

}
