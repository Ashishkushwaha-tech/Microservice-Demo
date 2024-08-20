package com.ashish.microservice.ratings_service.controller;

import com.ashish.microservice.ratings_service.model.Rating;
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
    public List<Rating> getRatingList(@PathVariable("movieId") String movieId) {
        return Arrays.asList(Rating.builder().movieId("211").rating(5).build(),
                Rating.builder().movieId("44").rating(4).build());
    }
    @GetMapping("/users/1/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId) {
        return Rating.builder().movieId("44").rating(4).build();
    }
}
