package com.teestore.backend.service;

import com.teestore.backend.dao.CarouselDAO;
import com.teestore.backend.model.Carousel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service( value = "carouselService")
@Transactional
public class CarouselServiceImpl implements CarouselService{
    
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private CarouselDAO carouselDAO;

    @Override
    public List<Carousel> getAllCarousel() throws Exception {

        List<Carousel> carouselList = carouselDAO.getAllCarousel();

        logger.debug("Getting Carousels...");
        if( carouselList == null || carouselList.isEmpty() )
            throw new Exception("CarouselService.CAROUSEL_LIST_NOT_FOUND");
        
        logger.debug("Carousels Received...");
        return carouselList;
    }

    @Override
    public String addCarousel(Carousel carousel) throws Exception{

        logger.debug("Adding Carousel...");
        if( carousel == null )
            throw new Exception("CarouselService.INVALID_CAROUSEL");

        String id = carouselDAO.addCarousel(carousel);

        if( id == null || id.equals("") )
            throw new Exception("CarouselService.CAROUSEL_NOT_ADDED");

        logger.debug("Carousel Added with id : " + id);
        return id;
    }

    @Override
    public Carousel getCarouselById(String carouselId) throws Exception{

        logger.debug("Getting Carousel with id " + carouselId);
        if( carouselId == null || carouselId.equals("") )
            throw new Exception("CarouselService.INVALID_CAROUSEL_ID");

        Carousel carousel = carouselDAO.getCarouselById(carouselId);

        if( carousel == null)
            throw new Exception("CarouselService.CAROUSEL_NOT_FOUND");

        logger.debug("Carousel Extracted...");
        return carousel;
    }

    @Override
    public String removeCarousel(String carouselId) throws Exception{

        logger.debug("Removing Carousel with id " + carouselId);
        if( carouselId == null || carouselId.equals("") )
            throw new Exception("CarouselService.INVALID_CAROUSEL_ID");

        String id = carouselDAO.removeCarousel(carouselId);

        if( id == null || id.equals("") )
            throw new Exception("CarouselService.CAROUSEL_NOT_DELETED");

        logger.debug("Carousel Deleted...");
        return id;
    }
}
