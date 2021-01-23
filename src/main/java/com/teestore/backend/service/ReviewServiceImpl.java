package com.teestore.backend.service;

import com.teestore.backend.dao.ReviewDAO;
import com.teestore.backend.enums.Rating;
import com.teestore.backend.model.RatingCounts;
import com.teestore.backend.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service(value = "reviewService")
@Transactional
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDAO reviewDAO;

    @Override
    public List<Review> getReviewByUserId(String userId) throws Exception {

        if (userId == null || userId.equals(""))
            throw new Exception("ReviewService.INVALID_USER_ID");

        List<Review> reviewList = reviewDAO.getReviewByUserId(userId);

        if (reviewList == null || reviewList.isEmpty())
            throw new Exception("ReviewService.REVIEW_LIST_NOT_FOUND");

        return reviewList;
    }

    @Override
    public List<Review> getReviewByProductId(String productId) throws Exception {

        if (productId == null || productId.equals(""))
            throw new Exception("ReviewService.INVALID_PRODUCT_ID");

        List<Review> reviewList = reviewDAO.getReviewByProductId(productId);

        if (reviewList == null || reviewList.isEmpty())
            throw new Exception("ReviewService.REVIEW_LIST_NOT_FOUND");

        return reviewList;
    }

    @Override
    public String addReview(Review review) throws Exception {

        if (review == null)
            throw new Exception("ReviewService.INVALID_REVIEW");

        String reviewId = reviewDAO.addReview(review);

        if (reviewId == null)
            throw new Exception("ReviewService.REVIEW_NOT_ADDED");

        return reviewId;
    }

    @Override
    public String editReview(String reviewId, Review review) throws Exception {

        if (reviewId == null || review == null || reviewId.equals(""))
            throw new Exception("ReviewService.INVALID_REVIEW");

        String rId = reviewDAO.editReview(reviewId, review);

        if (rId == null)
            throw new Exception("ReviewService.REVIEW_NOT_EDITED");

        return rId;
    }

    @Override
    public String deleteReview(String reviewId) throws Exception {

        if (reviewId == null || reviewId.equals(""))
            throw new Exception("ReviewService.INVALID_REVIEW_ID");

        String rId = reviewDAO.deleteReview(reviewId);

        if (rId == null)
            throw new Exception("ReviewService.REVIEW_NOT_DELETED");

        return rId;
    }

    @Override
    public Integer reviewHelpful(String reviewId, String userId) throws Exception {

        if (reviewId == null || userId == null || reviewId.equals("") || userId.equals(""))
            throw new Exception("ReviewService.INVALID_REVIEW_ID");

        Integer noOfHelpfulLikes = reviewDAO.reviewHelpful(reviewId);

        if (noOfHelpfulLikes == null)
            throw new Exception("ReviewService.REVIEW_NOT_HELPFUL");

        return noOfHelpfulLikes;
    }

    @Override
    public List<Review> getReviewByRating(String productId, Rating rating) throws Exception {

        if (productId == null || rating == null || productId.equals(""))
            throw new Exception("ReviewService.INVALID_PRODUCT_ID");

        List<Review> reviewList = reviewDAO.getReviewByRating(productId, rating);

        if (reviewList == null || reviewList.isEmpty())
            throw new Exception("ReviewService.REVIEW_LIST_NOT_FOUND");

        return reviewList;
    }

    @Override
    public List<Review> getTopReviewsByProduct(String productId, String userId) throws Exception {
        if (productId == null || productId.equals(""))
            throw new Exception("ReviewService.INVALID_PRODUCT_ID");

        List<Review> reviewList = reviewDAO.getTopReviewsForProduct(productId, userId);

        if (reviewList == null || reviewList.isEmpty())
            throw new Exception("ReviewService.REVIEW_LIST_NOT_FOUND");

        return reviewList;
    }

    @Override
    public RatingCounts getRatingCounts(String productId) throws Exception {
        if (productId == null || productId.equals(""))
            throw new Exception("ReviewService.INVALID_PRODUCT_ID");

        RatingCounts reviewList = reviewDAO.getRatingCounts(productId);

        if (reviewList == null)
            throw new Exception("ReviewService.RATINGS_NOT_RETRIEVED");

        return reviewList;
    }
}
