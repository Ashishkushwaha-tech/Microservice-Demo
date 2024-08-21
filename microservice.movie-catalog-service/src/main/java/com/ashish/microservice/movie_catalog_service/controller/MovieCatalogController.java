package com.ashish.microservice.movie_catalog_service.controller;

import com.ashish.microservice.movie_catalog_service.model.CatalogItem;
import com.ashish.microservice.movie_catalog_service.model.Movie;
import com.ashish.microservice.movie_catalog_service.model.Rating;
import com.ashish.microservice.movie_catalog_service.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movieCatalog")
public class MovieCatalogController {


    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{userId}")
    @HystrixCommand(fallbackMethod = "getFallbackCatalog")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        UserRating ratings= restTemplate.getForObject("http://MICROSERVICE.RATINGS-SERVICE/ratingsdata/users/" + userId, UserRating.class);
        ;
        return ratings.getRatingList().stream().map(rat -> {
            Movie movie = restTemplate.getForObject("http://MICROSERVICE.MOVIE/movieInfo/" + rat.getMovieId(), Movie.class);
            return new CatalogItem().builder()
                    .name(movie.getName())
                    .ratings(rat.getRating())
                    .description(movie.getDescription())
                    .build();
        }).collect(Collectors.toList());
    }

    public List<CatalogItem> getFallbackCatalog(String userId) {
        return Arrays.asList(CatalogItem.builder()
                .name("no movie")
                .description("nothing")
                .ratings(0)
                .build());
    }

}
