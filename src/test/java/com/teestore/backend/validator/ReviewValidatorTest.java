package com.teestore.backend.validator;

import com.teestore.backend.model.Review;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ReviewValidatorTest {

    @Test
    public void validateReviewValidTest() throws Exception{
        Review r = new Review();
        r.setReviewTitle("Sample Review Title");
        r.setReviewBody("Sample Review Body");
        ReviewValidator.validateReview(r);
    }

    @Test
    public void validateReviewInValidTestInvalidReviewTitle() throws Exception{
        Review r = new Review();
        r.setReviewTitle("Sample Review Title Sample Review Title Sample Review Title");
        r.setReviewBody("Sample Review Body");
        Exception e = Assert.assertThrows(Exception.class,
                ()-> ReviewValidator.validateReview(r));
        Assert.assertEquals("ReviewValidator.INVALID_TITLE", e.getMessage());
    }

    @Test
    public void validateReviewInValidTestInvalidReviewTitleNull() throws Exception{
        Review r = new Review();
        r.setReviewTitle(null);
        r.setReviewBody("Sample Review Body");
        Exception e = Assert.assertThrows(Exception.class,
                ()-> ReviewValidator.validateReview(r));
        Assert.assertEquals("ReviewValidator.INVALID_TITLE", e.getMessage());
    }

    @Test
    public void validateReviewInvalidTestInvalidReviewBody() throws Exception{
        Review r = new Review();
        r.setReviewTitle("Sample Review Title");
        r.setReviewBody("Sample Review Body Sample Review Body Sample Review Body Sample Review Body Sample Review Body" +
                "Sample Review Body Sample Review Body Sample Review Body Sample Review Body Sample Review Body" +
                "Sample Review Body Sample Review Body Sample Review Body Sample Review Body Sample Review Body" +
                "Sample Review Body Sample Review Body Sample Review Body Sample Review Body Sample Review Body");
        Exception e = Assert.assertThrows(Exception.class,
                ()-> ReviewValidator.validateReview(r));
        Assert.assertEquals("ReviewValidator.INVALID_BODY",e.getMessage());
    }

    @Test
    public void validateReviewInvalidTestInvalidReviewBodyNull() throws Exception{
        Review r = new Review();
        r.setReviewTitle("Sample Review Title");
        r.setReviewBody(null);
        Exception e = Assert.assertThrows(Exception.class,
                ()-> ReviewValidator.validateReview(r));
        Assert.assertEquals("ReviewValidator.INVALID_BODY",e.getMessage());
    }
}
