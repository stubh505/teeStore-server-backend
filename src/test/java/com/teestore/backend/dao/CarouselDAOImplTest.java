package com.teestore.backend.dao;

import com.teestore.backend.model.Carousel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class CarouselDAOImplTest {

    @Autowired
    private CarouselDAO carouselDAO;

    @Test
    public void getAllCarouselTest() throws Exception {
        List<Carousel> c = carouselDAO.getAllCarousel();
        Assert.assertNotNull(c);
        Assert.assertFalse(c.isEmpty());
    }

    @Test
    public void addCarouselValidTest() throws Exception {
        Carousel c = new Carousel();
        c.setLinkImage("http://imgur.com");

        String res = carouselDAO.addCarousel(c);
        Assert.assertNotNull(res);
    }

    @Test
    public void addCarouselInvalidTest() throws Exception {
        Carousel c = new Carousel();
        String res = carouselDAO.addCarousel(c);
        Assert.assertNull(res);
    }

    @Test
    public void getCarouselByIdInvalidTest() throws Exception {
        Carousel c = carouselDAO.getCarouselById("C1001");
        Assert.assertNull(c);
    }

    @Test
    public void removeCarouselValidTest() throws Exception {
        String res = carouselDAO.removeCarousel("C1001");
        Assert.assertNull(res);
    }
}
