package com.teestore.backend.model;

import com.teestore.backend.enums.Rating;

import java.time.LocalDateTime;

public class Review {
    private String reviewId;
    private String reviewTitle;
    private String reviewBody;
    private Rating ratings;
    private Integer ratingHelpful;
    private LocalDateTime reviewDate;
    private User user;
    private Product product;

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public String getReviewBody() {
        return reviewBody;
    }

    public void setReviewBody(String reviewBody) {
        this.reviewBody = reviewBody;
    }

    public Rating getRatings() {
        return ratings;
    }

    public void setRatings(Rating ratings) {
        this.ratings = ratings;
    }

    public Integer getRatingHelpful() {
        return ratingHelpful;
    }

    public void setRatingHelpful(Integer ratingHelpful) {
        this.ratingHelpful = ratingHelpful;
    }

    public LocalDateTime getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDateTime reviewDate) {
        this.reviewDate = reviewDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
