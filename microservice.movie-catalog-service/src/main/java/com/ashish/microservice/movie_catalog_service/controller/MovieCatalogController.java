package com.ashish.microservice.movie_catalog_service.controller;

import com.ashish.microservice.movie_catalog_service.model.CatalogItem;
import com.ashish.microservice.movie_catalog_service.model.Movie;
import com.ashish.microservice.movie_catalog_service.model.Rating;
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
    private WebClient.Builder webClientBuilder;

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){


       Rating rating=webClientBuilder.build().
               get().
               uri("http://localhost:9191/ratingsdata/users/1/"+userId).
               retrieve()
               .bodyToMono(Rating.class)
               .block();

        System.out.println(rating);
       List<Rating> ratings=new ArrayList<>();
       ratings.add(rating);

        return ratings.stream().map((rat)->{
            // For each movie ID, call movie info service and get details
            Movie movie =webClientBuilder.build()
                    .get()
                    .uri("http://localhost:9193/movieInfo/"+rat.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();
            // put them all together
            return new CatalogItem().builder().
                    name(movie.getName()).ratings(rat.getRating()).
                    description("test").build();
        }).collect(Collectors.toList());

    }
}
