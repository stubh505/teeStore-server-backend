package com.teestore.backend.service;

import com.teestore.backend.enums.Rating;
import com.teestore.backend.model.RatingCounts;
import com.teestore.backend.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getReviewByUserId(String userId) throws Exception;

    List<Review> getReviewByProductId(String productId) throws Exception;

    String addReview(Review review) throws Exception;

    String editReview(String reviewId, Review review) throws Exception;

    String deleteReview(String reviewId) throws Exception;

    Integer reviewHelpful(String reviewId, String userId) throws Exception;

    List<Review> getReviewByRating(String productId, Rating rating) throws Exception;

    List<Review> getTopReviewsByProduct(String productId, String userId) throws Exception;

    RatingCounts getRatingCounts(String productId) throws Exception;
}
