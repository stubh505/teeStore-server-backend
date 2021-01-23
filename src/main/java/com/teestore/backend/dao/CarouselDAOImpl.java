package com.teestore.backend.dao;

import com.teestore.backend.entity.CarouselEntity;
import com.teestore.backend.model.Carousel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository(value = "carouselDAO")
public class CarouselDAOImpl implements  CarouselDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Carousel> getAllCarousel() throws Exception {
        Query query= entityManager.createQuery("select c from CarouselEntity c");

        List<CarouselEntity> carouselEntityList = query.getResultList();
        List<Carousel> carouselList = null;

        if( carouselEntityList != null && !carouselEntityList.isEmpty() ){
            carouselList = new ArrayList<>();

            for( CarouselEntity carouselEntity: carouselEntityList) {
                Carousel carousel = new Carousel();

                carousel.setCarouselId(carouselEntity.getCarouselId());
                carousel.setLinkImage(carouselEntity.getLinkImage());
                String[] categoryType = carouselEntity.getLinkRoute().split(",");
                carousel.setFilter(categoryType[0]);
                carousel.setValue(categoryType[1]);

                carouselList.add(carousel);
            }
        }

        return carouselList;
    }

    @Override
    public String addCarousel(Carousel carousel) throws Exception {

        CarouselEntity carouselEntity = new CarouselEntity();

        carouselEntity.setLinkImage(carousel.getLinkImage());
        carouselEntity.setLinkRoute(carousel.getFilter()+","+carousel.getValue());

        entityManager.persist(carouselEntity);
        return carouselEntity.getCarouselId();
    }

    @Override
    public Carousel getCarouselById(String carouselId) throws Exception{

        CarouselEntity carouselEntity = entityManager.find( CarouselEntity.class, carouselId);

        if( carouselEntity == null )
            return null;

        Carousel carousel = new Carousel();
        carousel.setCarouselId(carouselEntity.getCarouselId());
        carousel.setLinkImage(carouselEntity.getLinkImage());
        String[] categoryType = carouselEntity.getLinkRoute().split(",");
        carousel.setFilter(categoryType[0]);
        carousel.setValue(categoryType[1]);

        return carousel;
    }

    @Override
    public String removeCarousel(String carouselId) throws Exception{

        CarouselEntity carouselEntity = entityManager.find( CarouselEntity.class, carouselId);
        String cId = null;

        if( carouselEntity != null ){

            cId = carouselEntity.getCarouselId();
            entityManager.remove( carouselEntity );
        }

        return cId;
    }
}
