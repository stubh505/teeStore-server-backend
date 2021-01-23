package com.teestore.backend.validator;

import com.teestore.backend.model.Review;

public class ReviewValidator {

    public static void validateReview(Review review) throws Exception {
        if (validateTitle(review.getReviewTitle()))
            throw new Exception("ReviewValidator.INVALID_TITLE");
        if (validateBody(review.getReviewBody()))
            throw new Exception("ReviewValidator.INVALID_BODY");
    }

    private static boolean validateTitle(String title) {
        return title == null || title.length() > 50;
    }

    private static boolean validateBody(String b) {
        return b == null || b.length() > 250;
    }
}