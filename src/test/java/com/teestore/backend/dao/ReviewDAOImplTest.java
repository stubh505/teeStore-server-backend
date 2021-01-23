package com.teestore.backend.dao;

import com.teestore.backend.enums.Rating;
import com.teestore.backend.model.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class ReviewDAOImplTest {

    @Autowired
    private ReviewDAO reviewDAO;

    @Test
    public void getReviewByUserIdValidTest () throws Exception {
        List<Review> contacts = reviewDAO.getReviewByUserId("U1002");
        Assert.assertNotNull(contacts);
        Assert.assertFalse(contacts.isEmpty());
    }

    @Test
    public void getReviewByUserIdInvalidTest () throws Exception {
        List<Review> contacts = reviewDAO.getReviewByUserId("U1000");
        Assert.assertNull(contacts);
    }

    @Test
    public void getReviewByProductIdValidTest () throws Exception {
        List<Review> contacts = reviewDAO.getReviewByProductId("P10001");
        Assert.assertNotNull(contacts);
    }

    @Test
    public void getReviewByProductIdInvalidTest () throws Exception {
        List<Review> contacts = reviewDAO.getReviewByProductId("P10000");
        Assert.assertNull(contacts);
    }

    @Test
    public void getReviewByRatingValidTest () throws Exception {
        List<Review> contacts = reviewDAO.getReviewByRating("P10001", Rating.FOUR);
        Assert.assertNotNull(contacts);
    }

    @Test
    public void getReviewByRatingInvalidTest () throws Exception {
        List<Review> contacts = reviewDAO.getReviewByRating("P10000", Rating.FOUR);
        Assert.assertNull(contacts);
    }

    @Test
    public void deleteReviewValidTest () throws Exception {
        String contacts = reviewDAO.deleteReview("R1001");
        Assert.assertTrue(contacts.matches("[R][0-9]{4}"));
    }

    @Test
    public void deleteReviewInvalidTest () throws Exception {
        String contacts = reviewDAO.deleteReview("P10000");
        Assert.assertNull(contacts);
    }

    @Test
    public void addReviewValidTest () throws Exception {
        Review r = new Review();
        Product p = new Product();
        User u = new User();
        u.setUserId("U1001");
        p.setProductId("P10001");
        r.setProduct(p);
        r.setUser(u);
        r.setRatings(Rating.FOUR);
        r.setReviewDate(LocalDateTime.now());
        r.setReviewTitle("This is a test review");
        r.setReviewBody("This is a test review body");
        String res = reviewDAO.addReview(r);
        Assert.assertTrue(res.matches("[R][0-9]{4}"));
    }

    @Test
    public void addReviewInvalidTestInvalidUser () throws Exception {
        Review r = new Review();
        Product p = new Product();
        User u = new User();
        u.setUserId("U1000");
        p.setProductId("P10001");
        r.setProduct(p);
        r.setUser(u);
        r.setRatings(Rating.FOUR);
        r.setReviewDate(LocalDateTime.now());
        r.setReviewTitle("This is a test review");
        r.setReviewBody("This is a test review body");
        String res = reviewDAO.addReview(r);
        Assert.assertNull(res);
    }

    @Test
    public void addReviewInvalidTestInvalidProduct () throws Exception {
        Review r = new Review();
        Product p = new Product();
        User u = new User();
        u.setUserId("U1001");
        p.setProductId("P10000");
        r.setProduct(p);
        r.setUser(u);
        r.setRatings(Rating.FOUR);
        r.setReviewDate(LocalDateTime.now());
        r.setReviewTitle("This is a test review");
        r.setReviewBody("This is a test review body");
        String res = reviewDAO.addReview(r);
        Assert.assertNull(res);
    }

    @Test
    public void editReviewValidTest () throws Exception {
        Review r = new Review();
        Product p = new Product();
        User u = new User();
        u.setUserId("U1001");
        p.setProductId("P10001");
        r.setProduct(p);
        r.setUser(u);
        r.setRatings(Rating.FOUR);
        r.setReviewDate(LocalDateTime.now());
        r.setReviewTitle("This is a test review");
        r.setReviewBody("This is a test review body");
        String res = reviewDAO.editReview("R1001",r);
        Assert.assertTrue(res.matches("[R][0-9]{4}"));
    }

    @Test
    public void editReviewInvalidTest () throws Exception {
        Review r = new Review();
        Product p = new Product();
        User u = new User();
        u.setUserId("U1001");
        p.setProductId("P10001");
        r.setProduct(p);
        r.setUser(u);
        r.setRatings(Rating.FOUR);
        r.setReviewDate(LocalDateTime.now());
        r.setReviewTitle("This is a test review");
        r.setReviewBody("This is a test review body");
        String res = reviewDAO.editReview("R1000",r);
        Assert.assertNull(res);
    }

    @Test
    public void editReviewInvalidTestInvalidUser () throws Exception {
        Review r = new Review();
        Product p = new Product();
        User u = new User();
        u.setUserId("U1000");
        p.setProductId("P10001");
        r.setProduct(p);
        r.setUser(u);
        r.setRatings(Rating.FOUR);
        r.setReviewDate(LocalDateTime.now());
        r.setReviewTitle("This is a test review");
        r.setReviewBody("This is a test review body");
        String res = reviewDAO.editReview("R1001",r);
        Assert.assertNull(res);
    }

    @Test
    public void editReviewInvalidTestInvalidProduct () throws Exception {
        Review r = new Review();
        Product p = new Product();
        User u = new User();
        u.setUserId("U1001");
        p.setProductId("P10000");
        r.setProduct(p);
        r.setUser(u);
        r.setRatings(Rating.FOUR);
        r.setReviewDate(LocalDateTime.now());
        r.setReviewTitle("This is a test review");
        r.setReviewBody("This is a test review body");
        String res = reviewDAO.editReview("R1001",r);
        Assert.assertNull(res);
    }

    @Test
    public void getRatingCountsValidTest() throws Exception {
        RatingCounts rc = reviewDAO.getRatingCounts("P10012");
        Assert.assertNotNull(rc);
    }
}
