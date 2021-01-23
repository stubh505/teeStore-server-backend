package com.teestore.backend.entity;

import com.teestore.backend.enums.Rating;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Review")
@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
)
public class ReviewEntity {
    @Id
    @GenericGenerator(name = "reviewIdGen", strategy = "com.teestore.backend.entity.generator.ReviewIdGenerator")
    @GeneratedValue(generator = "reviewIdGen")
    @Column(length = 5)
    private String reviewId;
    @Column(length = 50)
    private String reviewTitle;
    @Column(length = 500)
    private String reviewBody;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "rating_type")
    @Type(type = "pgsql_enum")
    private Rating ratings;
    private Integer ratingHelpful;
    private LocalDateTime reviewDate;
    @ManyToOne(cascade = CascadeType.ALL)
    private UserEntity user;
    @ManyToOne(cascade = CascadeType.ALL)
    private ProductEntity product;

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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
}
