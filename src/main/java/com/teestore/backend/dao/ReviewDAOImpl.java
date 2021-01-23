package com.teestore.backend.dao;

import com.teestore.backend.entity.ProductEntity;
import com.teestore.backend.entity.ReviewEntity;
import com.teestore.backend.entity.UserEntity;
import com.teestore.backend.enums.Rating;
import com.teestore.backend.model.Product;
import com.teestore.backend.model.RatingCounts;
import com.teestore.backend.model.Review;
import com.teestore.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository(value = "reviewDAO")
public class ReviewDAOImpl implements ReviewDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Review> getReviewByUserId(String userId) throws Exception {

        Query query = entityManager.createQuery("select r from ReviewEntity r where r.user.userId= :userId");
        query.setParameter("userId", userId);

        List<ReviewEntity> reviewEntityList = query.getResultList();
        List<Review> reviewList = null;

        if (reviewEntityList != null && !reviewEntityList.isEmpty()) {

            reviewList = new ArrayList<>();
            for (ReviewEntity reviewEntity : reviewEntityList) {
                Review review = new Review();
                review.setReviewId(reviewEntity.getReviewId());
                review.setReviewTitle(reviewEntity.getReviewTitle());
                review.setReviewBody(reviewEntity.getReviewBody());
                review.setReviewDate(reviewEntity.getReviewDate());
                review.setRatings(reviewEntity.getRatings());
                review.setRatingHelpful(reviewEntity.getRatingHelpful());

                UserEntity userEntity = reviewEntity.getUser();

                if (userEntity == null)
                    return null;

                User user = new User();
                user.setUserId(userEntity.getUserId());
                user.setUserName(userEntity.getUserName());
                review.setUser(user);

                ProductEntity productEntity = reviewEntity.getProduct();

                if (productEntity == null)
                    return null;

                Product product = new Product();
                product.setProductId(productEntity.getProductId());
                product.setProductName(productEntity.getProductName());

                review.setProduct(product);

                reviewList.add(review);
            }
        }
        return reviewList;
    }

    @Override
    public List<Review> getReviewByProductId(String productId) throws Exception {

        Query query = entityManager.createQuery("select r from ReviewEntity r where r.product.productId =:productId");
        query.setParameter("productId", productId);

        List<ReviewEntity> reviewEntityList = query.getResultList();
        List<Review> reviewList = null;

        if (reviewEntityList != null && !reviewEntityList.isEmpty()) {

            reviewList = new ArrayList<>();
            for (ReviewEntity reviewEntity : reviewEntityList) {
                Review review = new Review();
                review.setReviewId(reviewEntity.getReviewId());
                review.setReviewTitle(reviewEntity.getReviewTitle());
                review.setReviewBody(reviewEntity.getReviewBody());
                review.setReviewDate(reviewEntity.getReviewDate());
                review.setRatings(reviewEntity.getRatings());
                review.setRatingHelpful(reviewEntity.getRatingHelpful());

                UserEntity userEntity = reviewEntity.getUser();

                if (userEntity == null)
                    return null;

                User user = new User();
                user.setUserId(userEntity.getUserId());
                user.setUserName(userEntity.getUserName());
                review.setUser(user);

                ProductEntity productEntity = reviewEntity.getProduct();

                if (productEntity == null)
                    return null;

                Product product = new Product();
                product.setProductId(productEntity.getProductId());
                product.setProductName(productEntity.getProductName());

                review.setProduct(product);

                reviewList.add(review);
            }
        }
        return reviewList;
    }

    @Override
    public String addReview(Review review) throws Exception {

        if (review == null)
            return null;

        User user = review.getUser();

        if (user == null || user.getUserId() == null)
            return null;

        Product product = review.getProduct();

        if (product == null || product.getProductId() == null)
            return null;

        UserEntity userEntity = entityManager.find(UserEntity.class, user.getUserId());

        if (userEntity == null)
            return null;

        ProductEntity productEntity = entityManager.find(ProductEntity.class, product.getProductId());

        if (productEntity == null)
            return null;

        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setUser(userEntity);
        reviewEntity.setProduct(productEntity);
        reviewEntity.setReviewTitle(review.getReviewTitle());
        reviewEntity.setReviewBody(review.getReviewBody());
        reviewEntity.setReviewDate(LocalDateTime.now());
        reviewEntity.setRatingHelpful(0);
        reviewEntity.setRatings(review.getRatings());

        Rating rating = review.getRatings();
        Integer ratingInNo = null;
        if (rating == Rating.ONE)
            ratingInNo = 1;
        else if (rating == Rating.TWO)
            ratingInNo = 2;
        else if (rating == Rating.THREE)
            ratingInNo = 3;
        else if (rating == Rating.FOUR)
            ratingInNo = 4;
        else if (rating == Rating.FIVE)
            ratingInNo = 5;

        String avgRating = productEntity.getAvgRating();
        String[] ratingParameter = avgRating.split("\\.");
        int noOfRatings = Integer.parseInt(ratingParameter[1])+1;
        int totalRatings = Integer.parseInt(ratingParameter[0]);
        totalRatings += ratingInNo;
        String newAvgRating = totalRatings + "." + noOfRatings;

        productEntity.setAvgRating(newAvgRating);

        entityManager.persist(productEntity);
        entityManager.persist(reviewEntity);

        return reviewEntity.getReviewId();
    }

    @Override
    public String editReview(String reviewId, Review review) throws Exception {

        ReviewEntity reviewEntity = entityManager.find(ReviewEntity.class, reviewId);

        if (reviewEntity == null)
            return null;

        if (review.getReviewTitle() != null)
            reviewEntity.setReviewTitle(review.getReviewTitle());

        if (review.getReviewBody() != null)
            reviewEntity.setReviewBody(review.getReviewBody());

        if (review.getReviewDate() != null)
            reviewEntity.setReviewDate(review.getReviewDate());

        if (review.getRatings() != null) {

            reviewEntity.setRatings(review.getRatings());
            Product product = review.getProduct();

            if (product == null || product.getProductId() == null)
                return null;

            UserEntity userEntity = entityManager.find(UserEntity.class, review.getUser().getUserId());

            if (userEntity == null)
                return null;

            ProductEntity productEntity = entityManager.find(ProductEntity.class, product.getProductId());

            if (productEntity == null)
                return null;

            Rating rating = review.getRatings();
            Integer ratingInNo = null;
            if (rating == Rating.ONE)
                ratingInNo = 1;
            else if (rating == Rating.TWO)
                ratingInNo = 2;
            else if (rating == Rating.THREE)
                ratingInNo = 3;
            else if (rating == Rating.FOUR)
                ratingInNo = 4;
            else if (rating == Rating.FIVE)
                ratingInNo = 5;

            String avgRating = productEntity.getAvgRating();
            String[] ratingParameter = avgRating.split("\\.");
            int noOfRatings = Integer.parseInt(ratingParameter[1])+1;
            int totalRatings = Integer.parseInt(ratingParameter[0]);
            totalRatings += ratingInNo;
            String newAvgRating = totalRatings + "." + noOfRatings;

            productEntity.setAvgRating(newAvgRating);
            reviewEntity.setProduct(productEntity);
            entityManager.persist(productEntity);
        }

        entityManager.persist(reviewEntity);
        return reviewEntity.getReviewId();
    }

    @Override
    public String deleteReview(String reviewId) throws Exception {

        ReviewEntity reviewEntity = entityManager.find(ReviewEntity.class, reviewId);

        String rId = null;

        if (reviewEntity != null) {
            reviewEntity.setUser(null);
            reviewEntity.setProduct(null);
            entityManager.remove(reviewEntity);
            rId = reviewEntity.getReviewId();
        }

        return rId;
    }

    @Override
    public Integer reviewHelpful(String reviewId) throws Exception {

        ReviewEntity reviewEntity = entityManager.find(ReviewEntity.class, reviewId);
        Integer noOfHelpfulLikes = null;

        if (reviewEntity != null && reviewEntity.getUser() != null) {
            reviewEntity.setRatingHelpful(reviewEntity.getRatingHelpful() + 1);
            noOfHelpfulLikes = reviewEntity.getRatingHelpful();
        }

        return noOfHelpfulLikes;
    }

    @Override
    public List<Review> getReviewByRating(String productId, Rating rating) throws Exception {

        Query query = entityManager.createQuery("select r from ReviewEntity r where r.product.productId =:productId and r.ratings=:rating");
        query.setParameter("productId", productId);
        query.setParameter("rating", rating);

        List<ReviewEntity> reviewEntityList = query.getResultList();
        List<Review> reviewList = null;

        if (reviewEntityList != null && !reviewEntityList.isEmpty()) {

            reviewList = new ArrayList<>();
            for (ReviewEntity reviewEntity : reviewEntityList) {
                Review review = new Review();
                review.setReviewId(reviewEntity.getReviewId());
                review.setReviewTitle(reviewEntity.getReviewTitle());
                review.setReviewBody(reviewEntity.getReviewBody());
                review.setReviewDate(reviewEntity.getReviewDate());
                review.setRatings(reviewEntity.getRatings());
                review.setRatingHelpful(reviewEntity.getRatingHelpful());

                UserEntity userEntity = reviewEntity.getUser();

                if (userEntity == null)
                    return null;

                User user = new User();
                user.setUserId(userEntity.getUserId());
                user.setUserName(userEntity.getUserName());

                review.setUser(user);

                Product product = new Product();
                product.setProductId(productId);

                review.setProduct(product);

                reviewList.add(review);
            }

        }
        return reviewList;
    }

    @Override
    public List<Review> getTopReviewsForProduct(String productId, String userId) throws Exception {
        List<ReviewEntity> reviewEntityList;

        if (!userId.equals("null") && !userId.equals("")) {
            Query query1 = entityManager.createQuery("select r from ReviewEntity r where r.product.productId = :productId and r.user.userId = :userId");
            query1.setParameter("userId", userId);
            query1.setParameter("productId", productId);
            reviewEntityList = query1.getResultList();

            int max = 3;
            if (!reviewEntityList.isEmpty())
                max = 2;

            Query query = entityManager.createQuery("select r from ReviewEntity r where r.product.productId =:productId order by r.ratingHelpful desc");
            query.setParameter("productId", productId);
            reviewEntityList.addAll(query.setMaxResults(max).getResultList());

        } else {
            Query query = entityManager.createQuery("select r from ReviewEntity r where r.product.productId =:productId order by r.ratingHelpful desc");
            query.setParameter("productId", productId);
            reviewEntityList = query.setMaxResults(3).getResultList();
        }

        List<Review> reviewList = null;

        if (reviewEntityList != null && !reviewEntityList.isEmpty()) {

            reviewList = new ArrayList<>();
            for (ReviewEntity reviewEntity : reviewEntityList) {
                Review review = new Review();
                review.setReviewId(reviewEntity.getReviewId());
                review.setReviewTitle(reviewEntity.getReviewTitle());
                review.setReviewBody(reviewEntity.getReviewBody());
                review.setReviewDate(reviewEntity.getReviewDate());
                review.setRatings(reviewEntity.getRatings());
                review.setRatingHelpful(reviewEntity.getRatingHelpful());

                UserEntity userEntity = reviewEntity.getUser();

                if (userEntity == null)
                    return null;

                User user = new User();
                user.setUserId(userEntity.getUserId());
                user.setUserName(userEntity.getUserName());

                review.setUser(user);

                Product product = new Product();
                product.setProductId(productId);

                review.setProduct(product);

                reviewList.add(review);
            }

        }
        return reviewList;
    }

    @Override
    public RatingCounts getRatingCounts(String productId) throws Exception {
        Query query = entityManager.createQuery("select r.ratings, count(r) from ReviewEntity r where r.product.productId =:productId group by r.ratings");
        query.setParameter("productId", productId);

        List<Object[]> reviewCounts = query.getResultList();
        RatingCounts rc = new RatingCounts();

        for (Object[] o : reviewCounts) {
            if (o[0] == Rating.ONE)
                rc.setOne((int) (long) o[1]);
            else if (o[0] == Rating.THREE)
                rc.setThree((int) (long) o[1]);
            else if (o[0] == Rating.TWO)
                rc.setTwo((int) (long) o[1]);
            else if (o[0] == Rating.FIVE)
                rc.setFive((int) (long) o[1]);
            else if (o[0] == Rating.FOUR)
                rc.setFour((int) (long) o[1]);
        }

        return rc;
    }
}
