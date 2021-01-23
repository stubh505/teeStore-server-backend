package com.teestore.backend.dao;

import com.teestore.backend.enums.Rating;
import com.teestore.backend.model.RatingCounts;
import com.teestore.backend.model.Review;

import java.util.List;

public interface ReviewDAO {

    List<Review> getReviewByUserId(String userId) throws Exception;

    List<Review> getReviewByProductId(String productId) throws Exception;

    String addReview(Review review) throws Exception;

    String editReview(String reviewId, Review review) throws Exception;

    String deleteReview(String reviewId) throws Exception;

    Integer reviewHelpful(String reviewId) throws Exception;

    List<Review> getReviewByRating(String productId, Rating rating) throws Exception;

    List<Review> getTopReviewsForProduct(String productId, String userId) throws Exception;

    RatingCounts getRatingCounts(String productId) throws Exception;
}
