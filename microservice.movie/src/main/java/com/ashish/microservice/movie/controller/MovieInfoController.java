package com.ashish.microservice.movie.controller;

import com.ashish.microservice.movie.model.Movie;
import com.ashish.microservice.movie.model.MovieSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movieInfo")
public class MovieInfoController {

    @Value("${api.key}")
    private String apiKey;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{movieId}")
    public Movie getMovie(@PathVariable("movieId") String movieId){

        MovieSummary movieSummary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" +  apiKey, MovieSummary.class);
        return Movie.builder()
                .movieId(movieId)
                .name(movieSummary.getTitle())
                .description(movieSummary.getOverview()).build();
    }
}
