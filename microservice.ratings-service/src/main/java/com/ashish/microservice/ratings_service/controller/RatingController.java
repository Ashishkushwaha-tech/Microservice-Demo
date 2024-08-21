package com.ashish.microservice.ratings_service.controller;

import com.ashish.microservice.ratings_service.model.Rating;
import com.ashish.microservice.ratings_service.model.UserRating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingController {

    @GetMapping("/{movieId}")
    public Rating getRatings(@PathVariable("movieId") String movieId) {
        return new Rating(movieId,5);
    }

    @GetMapping("/users/{movieId}")
    public UserRating getRatingList(@PathVariable("movieId") String movieId) {
        return UserRating.builder().ratingList( Arrays.asList(Rating.builder().movieId("157336").rating(5).build(),
                Rating.builder().movieId("15733").rating(4).build(),Rating.builder().movieId("1573").rating(4).build())).build();
    }
    @GetMapping("/users/1/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId) {
        return Rating.builder().movieId("44").rating(4).build();
    }
}
