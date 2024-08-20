package com.ashish.microservice.movie_catalog_service.controller;

import com.ashish.microservice.movie_catalog_service.model.CatalogItem;
import com.ashish.microservice.movie_catalog_service.model.Movie;
import com.ashish.microservice.movie_catalog_service.model.Rating;
import com.ashish.microservice.movie_catalog_service.model.UserRating;
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

<<<<<<< HEAD

       Rating rating=webClientBuilder.build().
               get().
               uri("http://localhost:9191/ratingsdata/users/1/"+userId).
               retrieve()
               .bodyToMono(Rating.class)
               .block();

        System.out.println(rating);
       List<Rating> ratings=new ArrayList<>();
       ratings.add(rating);
        // get all rated movie Id's
        return ratings.stream().map((rat)->{
            Movie movie =webClientBuilder.build()
                    .get()
                    .uri("http://localhost:9193/movieInfo/"+rat.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();
=======
       UserRating ratings=restTemplate.getForObject("http://localhost:9191/ratingsdata/users/"+userId, UserRating.class);

        // get all rated movie Id's
        return ratings.getRatingList().stream().map((rat)->{
            Movie movie =restTemplate.getForObject("http://localhost:9193/movieInfo/"+rat.getMovieId(), Movie.class);
>>>>>>> 0d9a8ee
            return new CatalogItem().builder().
                    name(movie.getName()).ratings(rat.getRating()).
                    description("test").build();
        }).collect(Collectors.toList());
        // For each movie ID, call movie info service and get details

        // put them all together
//            return Arrays.asList(new CatalogItem().builder().name("dhammal").ratings(5).build(),
//                    new CatalogItem().builder().name("sammy").ratings(4).build());
    }
}
