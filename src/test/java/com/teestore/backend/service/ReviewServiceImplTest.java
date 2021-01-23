package com.teestore.backend.service;


import com.teestore.backend.dao.ReviewDAO;
import com.teestore.backend.enums.Rating;
import com.teestore.backend.model.Review;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ReviewServiceImplTest {

    @Mock
    private ReviewDAO reviewDAO;

    @InjectMocks
    private final ReviewService reviewService = new ReviewServiceImpl();

    @Test
    public void getReviewByUserIdValidTest () throws Exception {
        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review());
        Mockito.when(reviewDAO.getReviewByUserId(Mockito.anyString())).thenReturn(reviews);
        List<Review> res = reviewService.getReviewByUserId("U1001");
        Assert.assertNotNull(res);
    }

    @Test
    public void getReviewByUserIdInvalidTest () throws Exception {
        Mockito.when(reviewDAO.getReviewByUserId(Mockito.anyString())).thenReturn(null);
        Exception e = Assert.assertThrows(Exception.class,
                () -> reviewService.getReviewByUserId("C101"));
        Assert.assertEquals("ReviewService.REVIEW_LIST_NOT_FOUND", e.getMessage());
    }

    @Test
    public void getReviewByUserIdInvalidTestInvalidUserId () throws Exception {
        Exception e = Assert.assertThrows(Exception.class,
                () -> reviewService.getReviewByUserId(null));
        Assert.assertEquals("ReviewService.INVALID_USER_ID", e.getMessage());
    }

    @Test
    public void getReviewByRatingValidTest () throws Exception {
        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review());
        Mockito.when(reviewDAO.getReviewByRating(Mockito.any(), Mockito.eq(Rating.FOUR))).thenReturn(reviews);
        List<Review> res = reviewService.getReviewByRating("U1001", Rating.FOUR);
        Assert.assertNotNull(res);
    }

    @Test
    public void getReviewByReviewInvalidTest () throws Exception {
        Mockito.when(reviewDAO.getReviewByRating(Mockito.anyString(), Mockito.eq(Rating.FOUR))).thenReturn(null);
        Exception e = Assert.assertThrows(Exception.class,
                () -> reviewService.getReviewByRating("P10001", Rating.FOUR));
        Assert.assertEquals("ReviewService.REVIEW_LIST_NOT_FOUND", e.getMessage());
    }

    @Test
    public void getReviewByRatingInvalidTestInvalidUserId () throws Exception {
        Exception e = Assert.assertThrows(Exception.class,
                () -> reviewService.getReviewByRating(null, Rating.FOUR));
        Assert.assertEquals("ReviewService.INVALID_PRODUCT_ID", e.getMessage());
    }

    @Test
    public void getReviewByProductIdValidTest () throws Exception {
        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review());
        Mockito.when(reviewDAO.getReviewByProductId(Mockito.anyString())).thenReturn(reviews);
        List<Review> res = reviewService.getReviewByProductId("P10001");
        Assert.assertNotNull(res);
    }

    @Test
    public void getReviewByProductIdInvalidTest () throws Exception {
        Mockito.when(reviewDAO.getReviewByProductId(Mockito.anyString())).thenReturn(null);
        Exception e = Assert.assertThrows(Exception.class,
                () -> reviewService.getReviewByProductId("C101"));
        Assert.assertEquals("ReviewService.REVIEW_LIST_NOT_FOUND", e.getMessage());
    }

    @Test
    public void getReviewByProductIdInvalidTestInvalidProductId () throws Exception {
        Exception e = Assert.assertThrows(Exception.class,
                () -> reviewService.getReviewByProductId(null));
        Assert.assertEquals("ReviewService.INVALID_PRODUCT_ID", e.getMessage());
    }

    @Test
    public void addReviewValidTest () throws Exception {
        Review r = new Review();
        r.setReviewBody("Test review");
        r.setReviewTitle("Test review");
        Mockito.when(reviewDAO.addReview(Mockito.any())).thenReturn(Mockito.anyString());
        String res = reviewService.addReview(r);
        Assert.assertNotNull(res);
    }

    @Test
    public void addReviewInvalidTest () throws Exception {
        Mockito.when(reviewDAO.addReview(Mockito.any())).thenReturn(null);
        Review r = new Review();
        r.setReviewBody("Test review");
        r.setReviewTitle("Test review");

        Exception e = Assert.assertThrows(Exception.class,
                () -> reviewService.addReview(r));
        Assert.assertEquals("ReviewService.REVIEW_NOT_ADDED", e.getMessage());
    }

    @Test
    public void addReviewInvalidTestInvalidReview () throws Exception {
        Exception e = Assert.assertThrows(Exception.class,
                () -> reviewService.addReview(null));
        Assert.assertEquals("ReviewService.INVALID_REVIEW", e.getMessage());
    }

    @Test
    public void editReviewValidTest () throws Exception {
        Review r = new Review();
        r.setReviewBody("Test review");
        r.setReviewTitle("Test review");
        Mockito.when(reviewDAO.editReview(Mockito.eq("R1001"),Mockito.any())).thenReturn("R1001");
        String res = reviewService.editReview("R1001", r);
        Assert.assertNotNull(res);
    }

    @Test
    public void editReviewInvalidTest () throws Exception {
        Mockito.when(reviewDAO.editReview(Mockito.eq("R1001"),Mockito.any())).thenReturn(null);
        Review r = new Review();
        r.setReviewBody("Test review");
        r.setReviewTitle("Test review");

        Exception e = Assert.assertThrows(Exception.class,
                () -> reviewService.editReview("R0001", r));
        Assert.assertEquals("ReviewService.REVIEW_NOT_EDITED", e.getMessage());
    }

    @Test
    public void editReviewInvalidTestInvalidReview () throws Exception {
        Exception e = Assert.assertThrows(Exception.class,
                () -> reviewService.editReview(null,null));
        Assert.assertEquals("ReviewService.INVALID_REVIEW", e.getMessage());
    }

    @Test
    public void deleteReviewValidTest () throws Exception {
        Mockito.when(reviewDAO.deleteReview(Mockito.anyString())).thenReturn(Mockito.anyString());
        String res = reviewService.deleteReview("U1001");
        Assert.assertNotNull(res);
    }

    @Test
    public void deleteReviewInvalidTest () throws Exception {
        Mockito.when(reviewDAO.deleteReview(Mockito.anyString())).thenReturn(null);
        Review r = new Review();
        r.setReviewBody("Test review");
        r.setReviewTitle("Test review");

        Exception e = Assert.assertThrows(Exception.class,
                () -> reviewService.deleteReview("R0001"));
        Assert.assertEquals("ReviewService.REVIEW_NOT_DELETED", e.getMessage());
    }

    @Test
    public void deleteReviewInvalidTestInvalidReview () throws Exception {
        Exception e = Assert.assertThrows(Exception.class,
                () -> reviewService.deleteReview(null));
        Assert.assertEquals("ReviewService.INVALID_REVIEW_ID", e.getMessage());
    }

    @Test
    public void helpfulReviewValidTest () throws Exception {
        Mockito.when(reviewDAO.editReview(Mockito.anyString(),Mockito.any())).thenReturn(Mockito.anyString());
        Integer res = reviewService.reviewHelpful("R1001","U1001");
        Assert.assertNotNull(res);
    }

    @Test
    public void helpfulReviewInvalidTest () throws Exception {
        Mockito.when(reviewDAO.reviewHelpful(Mockito.anyString())).thenReturn(null);
        Review r = new Review();
        r.setReviewBody("Test review");
        r.setReviewTitle("Test review");

        Exception e = Assert.assertThrows(Exception.class,
                () -> reviewService.reviewHelpful("R001", "U1001"));
        Assert.assertEquals("ReviewService.REVIEW_NOT_HELPFUL", e.getMessage());
    }

    @Test
    public void helpfulReviewInvalidTestInvalidReview () throws Exception {
        Exception e = Assert.assertThrows(Exception.class,
                () -> reviewService.reviewHelpful("R1001",null));
        Assert.assertEquals("ReviewService.INVALID_REVIEW_ID", e.getMessage());
    }
}
