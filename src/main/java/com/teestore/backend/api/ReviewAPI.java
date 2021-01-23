package com.teestore.backend.api;

import com.teestore.backend.enums.Rating;
import com.teestore.backend.model.RatingCounts;
import com.teestore.backend.model.Review;
import com.teestore.backend.model.User;
import com.teestore.backend.service.ReviewService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("ReviewAPI")
public class ReviewAPI {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private Environment environment;

    @RequestMapping(value = "/getReviewByUserId/{userId}", method = RequestMethod.GET)
    public ResponseEntity<List<Review>> getReviewByUserId(@PathVariable String userId) throws Exception {

        try {
            return new ResponseEntity<>(reviewService.getReviewByUserId(userId), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

    }

    @RequestMapping(value = "/getReviewByProductId/{productId}", method = RequestMethod.GET)
    public ResponseEntity<List<Review>> getReviewByProductId(@PathVariable String productId) throws Exception {

        try {
            return new ResponseEntity<>(reviewService.getReviewByProductId(productId), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @RequestMapping(value = "/addReview", method = RequestMethod.POST)
    public ResponseEntity<String> addReview(@RequestBody Review review) throws Exception {

        try {
            return new ResponseEntity<>(reviewService.addReview(review), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
    }

    @RequestMapping(value = "/editReview/{reviewId}", method = RequestMethod.PUT)
    public ResponseEntity<String> editReview(@PathVariable String reviewId, @RequestBody Review review) throws Exception {

        try {
            return new ResponseEntity<>(reviewService.editReview(reviewId, review), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
    }

    @RequestMapping(value = "/deleteReview/{reviewId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteReview(@PathVariable String reviewId) throws Exception {

        try {
            return new ResponseEntity<>(reviewService.deleteReview(reviewId), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
    }

    @RequestMapping(value = "/reviewHelpful/{reviewId}", method = RequestMethod.PUT)
    public ResponseEntity<Integer> reviewHelpful(@PathVariable String reviewId, @RequestBody User user) throws Exception {

        try {
            return new ResponseEntity<>(reviewService.reviewHelpful(reviewId, user.getUserId()), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
    }

    @RequestMapping(value = "/getReviewByRating/{productId}", method = RequestMethod.GET)
    public ResponseEntity<List<Review>> getReviewByRating(@PathVariable String productId, @RequestBody Rating rating) throws Exception {

        try {
            return new ResponseEntity<>(reviewService.getReviewByRating(productId, rating), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping(value = "/getTopReviews")
    @ApiOperation("Retrieve top reviews for a product")
    public ResponseEntity<List<Review>> getTopReviews(@RequestParam String productId, @RequestParam(required = false, defaultValue = "null") String userId) throws Exception {
        try {
            return new ResponseEntity<>(reviewService.getTopReviewsByProduct(productId, userId), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping(value = "/getRatingCounts/{productId}")
    @ApiOperation("Get rating counts for a product")
    public ResponseEntity<RatingCounts> getRatingCounts(@PathVariable String productId) throws Exception {
        try {
            return new ResponseEntity<>(reviewService.getRatingCounts(productId), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
